package com.example.webcrawler.services;

import com.example.webcrawler.data.CrawlerConfiguration;
import com.example.webcrawler.data.Product;
import com.example.webcrawler.data.dtos.CrawlerConfigurationDTO;
import com.example.webcrawler.data.dtos.RetailerDto;
import com.example.webcrawler.exceptions.NoResults;
import com.example.webcrawler.utils.Utils;
import com.google.cloud.Tuple;
import com.google.cloud.bigquery.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

import static com.example.webcrawler.utils.Constans.*;

@Service
public class BigQueryService {

    private static final Logger logger = LoggerFactory.getLogger(BigQueryService.class);
    private BigQuery bigQuery;

    @Value("${bigquery.product.table}")
    private String productTable;

    @Value("${bigquery.crawlerConfiguration.table}")
    private String crawlerConfigurationTable;

    @Value("${bigquery.dataset}")
    private String dataset;

    @Value("${bigquery.project}")
    private String project;

    public BigQueryService(BigQuery bigQuery) {
        this.bigQuery = bigQuery;
    }

    private TableId crawlerConfigurationTableId() {

        return TableId.of(project, dataset, crawlerConfigurationTable);
    }

    private TableId productTableId() {

        return TableId.of(project, dataset, productTable);
    }



    public void saveCrawlerConfiguration(CrawlerConfiguration crawlerConfiguration) {
        logger.info("saveCrawlerConfiguration(): -> ENTER , crawlerConfiguration={}", crawlerConfiguration);
        String id;
        id = UUID.randomUUID().toString();
        InsertAllRequest insertAllRequest = InsertAllRequest.newBuilder(crawlerConfigurationTableId())
                .setRows(Collections.singletonList(crawlerConfigurationMapToDbRow(crawlerConfiguration, id)))
                .build();
        InsertAllResponse insertAllResponse = bigQuery.insertAll(insertAllRequest);
        if (insertAllResponse.hasErrors()) {
            insertAllResponse.getInsertErrors().forEach((index, error) -> {
                System.out.println(error);
            });
            throw new RuntimeException("Could not save feedback to big query.");
        }
        logger.info("saveCrawlerConfiguration(): -> EXIT");

    }

    public void saveProduct(Product product) {
        logger.info("saveProduct(): -> ENTER  product={}", product);
        String id;
        try {
            id = getId(product.getRetailerUuid(), product.getName());
        } catch (NoSuchElementException noSuchElementException) {
            id = UUID.randomUUID().toString();
        }

        if (id == null)
            id = UUID.randomUUID().toString();

        product.setUuid(id);
        InsertAllRequest insertAllRequest = InsertAllRequest.newBuilder(productTableId())
                .setRows(Collections.singletonList(productMapToDbRow(product)))
                .build();
        InsertAllResponse insertAllResponse = bigQuery.insertAll(insertAllRequest);
        if (insertAllResponse.hasErrors()) {
            insertAllResponse.getInsertErrors().forEach((index, error) -> {
                System.out.println(error);
            });
            throw new RuntimeException("Could not save feedback to big query.");
        }
        logger.info("saveProduct(): -> EXIT  product={}", product);
    }

    private InsertAllRequest.RowToInsert productMapToDbRow(Product product) {
        logger.info("productMapToDbRow(): -> ENTER");
        var values = productConvertToMap(product);
        return InsertAllRequest.RowToInsert.of(values);
    }

    private Map<String, Object> productConvertToMap(Product product) {
        logger.info("productConvertToMap(): -> ENTER");
        var rowData = new HashMap<String, Object>();
        rowData.put(ID, product.getUuid());
        rowData.put(RETAILER_ID, product.getRetailerUuid());
        rowData.put(RETAILER_NAME, product.getRetailerName());
        rowData.put(NAME, product.getName());
        rowData.put(IMAGE_URL, product.getImageUrl());
        rowData.put(PRICE_AMOUNT, product.getPriceAmount());
        rowData.put(PRICE_CURRENCY, product.getPriceCurrency());
        rowData.put(CATEGORY, product.getCategory());
        rowData.put(BRAND, product.getBrand());
        rowData.put(DESCRIPTION, product.getDescription());
        rowData.put(SPECIFICATIONS, product.getSpecifications().stream().map(list -> Utils.concatenate(list.getHeader(), list.getName(), list.getValue())).toList());
        rowData.put(CODE, product.getCode());


        return rowData;
    }


    private InsertAllRequest.RowToInsert crawlerConfigurationMapToDbRow(CrawlerConfiguration crawlerConfiguration, String id) {
        var values = crawlerConfigurationConvertToMap(crawlerConfiguration, id);
        return InsertAllRequest.RowToInsert.of(values);
    }


    private Map<String, Object> crawlerConfigurationConvertToMap(CrawlerConfiguration crawlerConfiguration, String id) {
        var rowData = new HashMap<String, Object>();
        rowData.put(ID, id);
        rowData.put(RETAILER_ID, crawlerConfiguration.getRetailerUuid());
        rowData.put(RETAILER_NAME, crawlerConfiguration.getRetailerName());
        rowData.put(MAIN_PAGE_URL, crawlerConfiguration.getMainPageUrl());
        rowData.put(PRODUCT_NAME, crawlerConfiguration.getProductNamePaths());
        rowData.put(PRODUCT_IMAGE, crawlerConfiguration.getProductImagePaths());
        rowData.put(PRODUCT_PRICE, crawlerConfiguration.getProductPricePaths());
        rowData.put(PRODUCT_BRAND, crawlerConfiguration.getProductBrandPaths());
        rowData.put(PRODUCT_SPECIFICATIONS, crawlerConfiguration.getProductSpecsPaths());
        rowData.put(PRODUCT_SPECIFICATIONS_BUTTON, crawlerConfiguration.getProductSpecsButtonPaths());
        rowData.put(PRODUCT_DESCRIPTION, crawlerConfiguration.getProductDescriptionPaths());
        rowData.put(PRODUCT_DESCRIPTION_BUTTON, crawlerConfiguration.getProductDescriptionButtonPaths());
        rowData.put(PRODUCT_COOKIES_BUTTON, crawlerConfiguration.getProductCookiesButtonPaths());
        rowData.put(PRODUCT_OTHER_BUTTON, crawlerConfiguration.getProductOtherButtonPaths());
        rowData.put(PRODUCT_CODE, crawlerConfiguration.getProductCodePaths());
        return rowData;
    }



    public String getId(String retailerUuid, String name) {
        String tableId = Utils.createTable(project, dataset, productTable);
        String query = "SELECT uuid FROM `" + tableId + "` where retailer_uuid = '" + retailerUuid + "' and UPPER(name) = '" + name.toUpperCase() + "'  ORDER BY creation_datetime DESC LIMIT 1";

        QueryJobConfiguration queryJobConfiguration = QueryJobConfiguration.newBuilder(query).setUseLegacySql(false).build();
        try {
            //logger.info("Query: " + queryConfig.getQuery());
            Set<String> entries = new LinkedHashSet<>();
            JobId jobId = JobId.of(UUID.randomUUID().toString());
            Job queryJob = bigQuery.create(JobInfo.newBuilder(queryJobConfiguration).setJobId(jobId).build());
            queryJob = queryJob.waitFor();
            handleJobErrors(queryJob);
            TableResult result = queryJob.getQueryResults();
            for (FieldValueList row : result.iterateAll()) {
                return row.get(ID).getStringValue();
            }
        } catch (InterruptedException e) {
            throw new RuntimeException("Task interrupted", e);
        }
        return null;
    }

    public CrawlerConfiguration getConfiguration(String retailerUuid) {
        logger.info("getConfiguration(): -> ENTER , retailerUuid={}", retailerUuid);

        String tableId = Utils.createTable(project, dataset, crawlerConfigurationTable);
        String query = "SELECT * FROM `" + tableId + "` where retailer_uuid = '" + retailerUuid + "' ORDER BY creation_datetime DESC LIMIT 1";

        QueryJobConfiguration queryJobConfiguration = QueryJobConfiguration.newBuilder(query).setUseLegacySql(false).build();
        CrawlerConfiguration crawlerConfiguration = new CrawlerConfiguration();
        try {
            //logger.info("Query: " + queryConfig.getQuery());
            Set<String> entries = new LinkedHashSet<>();
            JobId jobId = JobId.of(UUID.randomUUID().toString());
            Job queryJob = bigQuery.create(JobInfo.newBuilder(queryJobConfiguration).setJobId(jobId).build());
            queryJob = queryJob.waitFor();
            handleJobErrors(queryJob);
            TableResult result = queryJob.getQueryResults();
            for (FieldValueList row : result.iterateAll()) {
                crawlerConfiguration.setUuid(row.get(ID).getStringValue());
                crawlerConfiguration.setRetailerUuid(row.get(RETAILER_ID).getStringValue());
                crawlerConfiguration.setRetailerName(row.get(RETAILER_NAME).getValue() != null ? row.get(RETAILER_NAME).getStringValue() : "");
                crawlerConfiguration.setMainPageUrl(row.get(MAIN_PAGE_URL).getStringValue());
                crawlerConfiguration.setProductNamePaths(row.get(PRODUCT_NAME).getRepeatedValue().stream().map(FieldValue::getStringValue).toList());
                crawlerConfiguration.setProductImagePaths(row.get(PRODUCT_IMAGE).getRepeatedValue().stream().map(FieldValue::getStringValue).toList());
                crawlerConfiguration.setProductPricePaths(row.get(PRODUCT_PRICE).getRepeatedValue().stream().map(FieldValue::getStringValue).toList());
                crawlerConfiguration.setProductBrandPaths(row.get(PRODUCT_BRAND).getRepeatedValue().stream().map(FieldValue::getStringValue).toList());
                crawlerConfiguration.setProductSpecsPaths(row.get(PRODUCT_SPECIFICATIONS).getRepeatedValue().stream().map(FieldValue::getStringValue).toList());
                crawlerConfiguration.setProductSpecsButtonPaths(row.get(PRODUCT_SPECIFICATIONS_BUTTON).getRepeatedValue().stream().map(FieldValue::getStringValue).toList());
                crawlerConfiguration.setProductDescriptionPaths(row.get(PRODUCT_DESCRIPTION).getRepeatedValue().stream().map(FieldValue::getStringValue).toList());
                crawlerConfiguration.setProductDescriptionButtonPaths(row.get(PRODUCT_DESCRIPTION_BUTTON).getRepeatedValue().stream().map(FieldValue::getStringValue).toList());
                crawlerConfiguration.setProductCookiesButtonPaths(row.get(PRODUCT_COOKIES_BUTTON).getRepeatedValue().stream().map(FieldValue::getStringValue).toList());
                crawlerConfiguration.setProductOtherButtonPaths(row.get(PRODUCT_OTHER_BUTTON).getRepeatedValue().stream().map(FieldValue::getStringValue).toList());
                crawlerConfiguration.setProductCodePaths(row.get(PRODUCT_CODE).getRepeatedValue().stream().map(FieldValue::getStringValue).toList());
                logger.info("getConfiguration(): -> EXIT, retailerUuid={}, crawlerConfiguration={}", retailerUuid, crawlerConfiguration);
                return crawlerConfiguration;
            }
            throw new NoResults("configuration with retailerUuid " + retailerUuid + " not found");

        } catch (InterruptedException e) {
            throw new RuntimeException("Task interrupted", e);
        }
    }



    public Product getProduct(String retailerUuid, String name) {
        String tableId = Utils.createTable(project, dataset, productTable);

        String query = "SELECT * FROM `" + tableId + "` where retailer_uuid = '" + retailerUuid + "' AND UPPER(name) = '" + name.toUpperCase() + "' ORDER BY creation_datetime DESC LIMIT 1";
        return executeQueryProduct(query).getFirst();
    }

    public Product getProductById(String id) {
        String tableId = Utils.createTable(project, dataset, productTable);
        String query = "SELECT * FROM `" + tableId + "` where uuid = '" + id + "' ORDER BY creation_datetime DESC LIMIT 1";
        return executeQueryProduct(query).getFirst();
    }

    public List<Product> getProductsByFilter(String retailerUuid, String category, String brand, String code) {
        String query = handleQuery(retailerUuid, category, brand, code);
        return executeQueryProduct(query);
    }

    public List<String> getCategories(String retailerUuid) {
        String tableId = Utils.createTable(project, dataset, productTable);
        String query = "SELECT DISTINCT category as string FROM `" + tableId + "` WHERE retailer_uuid = '" + retailerUuid + "'";
        return executeQueryString(query);
    }

    public List<String> getBrands(String retailerUuid, String category) {
        logger.info("getBrands(): -> ENTER , retailerUuid={}, category={}", retailerUuid, category);
        String tableId = Utils.createTable(project, dataset, productTable);
        String query = "SELECT DISTINCT brand as string FROM `" + tableId + "` WHERE retailer_uuid = '" + retailerUuid + "' AND category = '" + category + "'";
        logger.info("getBrands():" + query);
        return executeQueryString(query);
    }

    public Boolean existsBrand(String retailerUuid, String brand) {
        String tableId = Utils.createTable(project, dataset, productTable);
        String query = "SELECT " +
                "    EXISTS ( " +
                "        SELECT 1 " +
                "        FROM `" + tableId + "` " +
                "        WHERE retailer_uuid = '" + retailerUuid + "' AND brand = '" + brand + "'" +
                "    ) AS exists_brand_for_retailer";

        return executeQueryBoolean(query);
    }


    private Tuple<Boolean, String> insertPrefixCondition(boolean isFirst, String condition) {
        if (isFirst) {
            isFirst = false;
            condition = condition
                    .concat(" WHERE ");
        } else {
            condition = condition
                    .concat(" AND ");
        }
        return Tuple.of(isFirst, condition);
    }

    private String handleQuery(String retailerUuid, String category, String brand, String code) {

        String condition = "";
        boolean isFirst = true;
        if (retailerUuid != null && !retailerUuid.isEmpty()) {
            Tuple<Boolean, String> tuple = insertPrefixCondition(isFirst, condition);
            condition = tuple.y();
            isFirst = tuple.x();
            condition = condition
                    .concat(" retailer_uuid = '" + retailerUuid + "'");
        }

        if (category != null && !category.isEmpty()) {
            Tuple<Boolean, String> tuple = insertPrefixCondition(isFirst, condition);
            condition = tuple.y();
            isFirst = tuple.x();
            condition = condition
                    .concat(" UPPER(category) = '" + category.toUpperCase() + "'");
        }

        if (brand != null && !brand.isEmpty()) {
            Tuple<Boolean, String> tuple = insertPrefixCondition(isFirst, condition);
            condition = tuple.y();
            isFirst = tuple.x();
            condition = condition
                    .concat(" UPPER(brand) = '" + brand.toUpperCase() + "'");
        }

        if (code != null && !code.isEmpty()) {
            Tuple<Boolean, String> tuple = insertPrefixCondition(isFirst, condition);
            condition = tuple.y();
            isFirst = tuple.x();
            condition = condition
                    .concat(" code = '" + code + "'");
        }

        String tableId = Utils.createTable(project, dataset, productTable);
        return "WITH ranked_products AS ( " +
                "    SELECT *, " +
                "           ROW_NUMBER() OVER (PARTITION BY uuid ORDER BY creation_datetime DESC) AS row_num " +
                "    FROM `" + tableId + "`" + condition + ") " +
                "SELECT * " +
                "FROM ranked_products " +
                "WHERE row_num = 1 " +
                "ORDER BY creation_datetime DESC; ";
    }

    private Boolean executeQueryBoolean(String query) {
        QueryJobConfiguration queryJobConfiguration = QueryJobConfiguration.newBuilder(query).setUseLegacySql(false).build();
        List<String> strings = new ArrayList<>();
        try {
            //logger.info("Query: " + queryConfig.getQuery());
            JobId jobId = JobId.of(UUID.randomUUID().toString());
            Job queryJob = bigQuery.create(JobInfo.newBuilder(queryJobConfiguration).setJobId(jobId).build());
            queryJob = queryJob.waitFor();
            handleJobErrors(queryJob);
            TableResult result = queryJob.getQueryResults();
            for (FieldValueList row : result.iterateAll()) {

                return row.get("exists_brand_for_retailer").getBooleanValue();
            }
            return false;
        } catch (InterruptedException e) {
            throw new RuntimeException("Task interrupted", e);
        }

    }

    private List<String> executeQueryString(String query) {
        QueryJobConfiguration queryJobConfiguration = QueryJobConfiguration.newBuilder(query).setUseLegacySql(false).build();
        List<String> strings = new ArrayList<>();
        try {
            //logger.info("Query: " + queryConfig.getQuery());
            JobId jobId = JobId.of(UUID.randomUUID().toString());
            Job queryJob = bigQuery.create(JobInfo.newBuilder(queryJobConfiguration).setJobId(jobId).build());
            queryJob = queryJob.waitFor();
            handleJobErrors(queryJob);
            TableResult result = queryJob.getQueryResults();
            for (FieldValueList row : result.iterateAll()) {

                strings.add(row.get(STRING).getStringValue());
            }
            return strings;
        } catch (InterruptedException e) {
            throw new RuntimeException("Task interrupted", e);
        }

    }

    private List<Product> executeQueryProduct(String query) {
        QueryJobConfiguration queryJobConfiguration = QueryJobConfiguration.newBuilder(query).setUseLegacySql(false).build();
        List<Product> products = new ArrayList<>();
        try {
            //logger.info("Query: " + queryConfig.getQuery());
            JobId jobId = JobId.of(UUID.randomUUID().toString());
            Job queryJob = bigQuery.create(JobInfo.newBuilder(queryJobConfiguration).setJobId(jobId).build());
            queryJob = queryJob.waitFor();
            handleJobErrors(queryJob);
            TableResult result = queryJob.getQueryResults();
            for (FieldValueList row : result.iterateAll()) {
                Product product = new Product();
                product.setUuid(row.get(ID).getStringValue());
                product.setName(row.get(NAME).getStringValue());
                product.setImageUrl(row.get(IMAGE_URL).getStringValue());
                product.setPriceAmount(row.get(PRICE_AMOUNT).getDoubleValue());
                product.setPriceCurrency(row.get(PRICE_CURRENCY).getStringValue());
                product.setCategory(row.get(CATEGORY).getStringValue());
                product.setBrand(row.get(BRAND).getStringValue());
                product.setDescription(row.get(DESCRIPTION).getStringValue());
                product.setRetailerName(row.get(RETAILER_NAME).getStringValue());
                product.setRetailerUuid(row.get(RETAILER_ID).getStringValue());
                product.setSpecifications(row.get(SPECIFICATIONS).getRepeatedValue()
                        .stream().map(FieldValue::getStringValue).toList()
                        .stream().map(Utils::split)
                        .collect(Collectors.toList()));
                product.setCode(row.get(CODE).getStringValue());

                products.add(product);
            }
            return products;
        } catch (InterruptedException e) {
            throw new RuntimeException("Task interrupted", e);
        }
    }


    protected <T> Set<T> query(QueryJobConfiguration queryConfig, Function<FieldValueList, T> rowMapper) {
        try {
            //logger.info("Query: " + queryConfig.getQuery());
            Set<T> entries = new LinkedHashSet<>();
            JobId jobId = JobId.of(UUID.randomUUID().toString());
            Job queryJob = bigQuery.create(JobInfo.newBuilder(queryConfig).setJobId(jobId).build());
            queryJob = queryJob.waitFor();
            handleJobErrors(queryJob);
            TableResult result = queryJob.getQueryResults();
            for (FieldValueList row : result.iterateAll()) {
                entries.add(rowMapper.apply(row));
            }
            return entries;
        } catch (InterruptedException e) {
            throw new RuntimeException("Task interrupted", e);
        }
    }

    private static void handleJobErrors(Job queryJob) {
        if (queryJob == null) {
            throw new RuntimeException("Job no longer exists");
        } else if (queryJob.getStatus().getError() != null) {
            throw new RuntimeException(queryJob.getStatus().getError().toString());
        }
    }


    public List<RetailerDto> getRetailers() {
        String query = "SELECT DISTINCT\n" +
                "  retailer_uuid,\n" +
                "  retailer_name\n" +
                "FROM\n" +
                "  `personal-project-428121.web_crawler.crawler_configuration`;\n";
        QueryJobConfiguration queryJobConfiguration = QueryJobConfiguration.newBuilder(query).setUseLegacySql(false).build();

        List<RetailerDto> retailerDtos = new ArrayList<>();
        try {
            //logger.info("Query: " + queryConfig.getQuery());
            JobId jobId = JobId.of(UUID.randomUUID().toString());
            Job queryJob = bigQuery.create(JobInfo.newBuilder(queryJobConfiguration).setJobId(jobId).build());
            queryJob = queryJob.waitFor();
            handleJobErrors(queryJob);
            TableResult result = queryJob.getQueryResults();
            for (FieldValueList row : result.iterateAll()) {
                retailerDtos.add(new RetailerDto(row.get(RETAILER_ID).getStringValue(),row.get(RETAILER_NAME).getStringValue()));
            }
            return retailerDtos;
        } catch (InterruptedException e) {
            throw new RuntimeException("Task interrupted", e);
        }


    }
}

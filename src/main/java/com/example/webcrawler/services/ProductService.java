package com.example.webcrawler.services;

import com.example.webcrawler.data.*;
import com.example.webcrawler.data.dtos.SaveProductMetadataDTO;
import com.example.webcrawler.data.dtos.RequestTestDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {

    @Autowired
    private WebCrawlingService webCrawlingService;
    @Autowired
    private BigQueryService bigQueryService;
    @Autowired
    private CrawlerConfigurationService crawlerConfigurationService;

    @Autowired
    private CrawlerConfigurationMapper crawlerConfigurationMapper;

    private static final Logger logger = LoggerFactory.getLogger(WebCrawlingService.class);


    public List<Product> saveProductsFromUrlsAndManuals(SaveProductMetadataDTO saveProductMetadataDto) {
        logger.info("saveProductsFromUrlsAndManuals(): ENTER -> retailerUuid={}", saveProductMetadataDto.retailerUuid());

        CrawlerConfiguration crawlerConfiguration = bigQueryService.getConfiguration(saveProductMetadataDto.retailerUuid());




        List<Product> products = webCrawlingService.searchOnDetailView(crawlerConfiguration,
                saveProductMetadataDto.category(),
                saveProductMetadataDto.urls());

        for (Product product : products) {
            bigQueryService.saveProduct(product);
        }
        logger.info("saveProductsFromUrlsAndManuals(): products were saved successfully");
        logger.info("saveProductsFromUrlsAndManuals(): EXIT -> retailerName={}", crawlerConfiguration.getRetailerName());
        return products;
    }

    public void saveTestProductsFromUrlsAndManuals(RequestTestDTO requestTestDTO){
        logger.info("saveProductsFromUrlsAndManuals(): ENTER -> retailerName={}", requestTestDTO.crawlerConfigurationDTO().retailerName());
        CrawlerConfiguration crawlerConfiguration = crawlerConfigurationMapper.fromDto(requestTestDTO.crawlerConfigurationDTO());


        webCrawlingService.searchOnDetailViewTest(crawlerConfiguration,
                requestTestDTO.urls());
        logger.info("saveTest completed successfully");
        logger.info("saveProductsFromUrlsAndManuals(): EXIT -> retailerName={}", requestTestDTO.crawlerConfigurationDTO().retailerName());
    }


    public List<Product> filterProductsBy(String retailerUuid, String category, String brand, String code) {
        return bigQueryService.getProductsByFilter(retailerUuid, category, brand, code);
    }


    public Product getProductById(String id) {
        return bigQueryService.getProductById(id);
    }


    public List<String> getCategories(String retailerUuid) {
        return bigQueryService.getCategories(retailerUuid);
    }

    public List<String> getBrands(String retailerUuid, String category) {
        return bigQueryService.getBrands(retailerUuid, category);
    }

    public Boolean existsBrand(String retailerUuid, String brand) {
        return bigQueryService.existsBrand(retailerUuid, brand);
    }

}

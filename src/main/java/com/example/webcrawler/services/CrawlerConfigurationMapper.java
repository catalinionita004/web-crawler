package com.example.webcrawler.services;

import com.example.webcrawler.data.CrawlerConfiguration;
import com.example.webcrawler.data.dtos.CrawlerConfigurationDTO;
import org.springframework.stereotype.Component;

import java.util.ArrayList;

@Component
public class CrawlerConfigurationMapper {

    public CrawlerConfiguration fromDto(CrawlerConfigurationDTO crawlerConfigurationDTO) {
        CrawlerConfiguration crawlerConfiguration = new CrawlerConfiguration();

        crawlerConfiguration.setRetailerUuid(crawlerConfigurationDTO.retailerUuid() != null ? crawlerConfigurationDTO.retailerUuid() : "");
        crawlerConfiguration.setRetailerName(crawlerConfigurationDTO.retailerName() != null ? crawlerConfigurationDTO.retailerName() : "");
        crawlerConfiguration.setMainPageUrl(crawlerConfigurationDTO.mainPageUrl() != null ? crawlerConfigurationDTO.mainPageUrl() : "");
        crawlerConfiguration.setProductNamePaths(crawlerConfigurationDTO.productNamePaths() != null ? crawlerConfigurationDTO.productNamePaths().stream().distinct().toList() : new ArrayList<>());
        crawlerConfiguration.setProductImagePaths(crawlerConfigurationDTO.productImagePaths() != null ? crawlerConfigurationDTO.productImagePaths().stream().distinct().toList() : new ArrayList<>());
        crawlerConfiguration.setProductPricePaths(crawlerConfigurationDTO.productPricePaths() != null ? crawlerConfigurationDTO.productPricePaths().stream().distinct().toList() : new ArrayList<>());
        crawlerConfiguration.setProductBrandPaths(crawlerConfigurationDTO.productBrandPaths() != null ? crawlerConfigurationDTO.productBrandPaths().stream().distinct().toList() : new ArrayList<>());
        crawlerConfiguration.setProductDescriptionPaths(crawlerConfigurationDTO.productDescriptionPaths() != null ? crawlerConfigurationDTO.productDescriptionPaths().stream().distinct().toList() : new ArrayList<>());
        crawlerConfiguration.setProductDescriptionButtonPaths(crawlerConfigurationDTO.productDescriptionButtonPaths() != null ? crawlerConfigurationDTO.productDescriptionButtonPaths().stream().distinct().toList() : new ArrayList<>());
        crawlerConfiguration.setProductSpecsPaths(crawlerConfigurationDTO.productSpecsPaths() != null ? crawlerConfigurationDTO.productSpecsPaths().stream().distinct().toList() : new ArrayList<>());
        crawlerConfiguration.setProductSpecsButtonPaths(crawlerConfigurationDTO.productSpecsButtonPaths() != null ? crawlerConfigurationDTO.productSpecsButtonPaths().stream().distinct().toList() : new ArrayList<>());
        crawlerConfiguration.setProductCookiesButtonPaths(crawlerConfigurationDTO.productCookiesButtonPaths() != null ? crawlerConfigurationDTO.productCookiesButtonPaths().stream().distinct().toList() : new ArrayList<>());
        crawlerConfiguration.setProductOtherButtonPaths(crawlerConfigurationDTO.productOtherButtonPaths() != null ? crawlerConfigurationDTO.productOtherButtonPaths().stream().distinct().toList() : new ArrayList<>());
        crawlerConfiguration.setProductCodePaths(crawlerConfigurationDTO.productCodePaths() != null ? crawlerConfigurationDTO.productCodePaths().stream().distinct().toList() : new ArrayList<>());
        return crawlerConfiguration;
    }


    public CrawlerConfigurationDTO toDto(CrawlerConfiguration crawlerConfiguration) {
        return new CrawlerConfigurationDTO(
                crawlerConfiguration.getRetailerName(),
                crawlerConfiguration.getRetailerUuid(),
                crawlerConfiguration.getMainPageUrl(),
                crawlerConfiguration.getProductNamePaths(),
                crawlerConfiguration.getProductImagePaths(),
                crawlerConfiguration.getProductPricePaths(),
                crawlerConfiguration.getProductBrandPaths(),
                crawlerConfiguration.getProductSpecsPaths(),
                crawlerConfiguration.getProductDescriptionPaths(),
                crawlerConfiguration.getProductSpecsButtonPaths(),
                crawlerConfiguration.getProductDescriptionButtonPaths(),
                crawlerConfiguration.getProductCookiesButtonPaths(),
                crawlerConfiguration.getProductOtherButtonPaths(),
                crawlerConfiguration.getProductCodePaths());
    }

}

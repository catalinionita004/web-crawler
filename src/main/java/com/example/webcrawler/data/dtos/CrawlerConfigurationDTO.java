package com.example.webcrawler.data.dtos;

import java.util.List;


public record CrawlerConfigurationDTO(String retailerName, String retailerUuid, String mainPageUrl,
                                      List<String> productNamePaths, List<String> productImagePaths,
                                      List<String> productPricePaths, List<String> productBrandPaths,
                                      List<String> productSpecsPaths, List<String> productDescriptionPaths,
                                      List<String> productSpecsButtonPaths, List<String> productDescriptionButtonPaths,
                                      List<String> productCookiesButtonPaths, List<String> productOtherButtonPaths,
                                      List<String> productCodePaths) {


}

package com.example.webcrawler.data;

import org.apache.commons.net.ntp.TimeStamp;

import java.util.List;

public class CrawlerConfiguration {

    private String uuid;
    private String retailerName;
    private String retailerUuid;
    private String mainPageUrl;
    private List<String> productNamePaths;
    private List<String> productImagePaths;
    private List<String> productPricePaths;
    private List<String> productBrandPaths;
    private List<String> productSpecsPaths;
    private List<String> productDescriptionPaths;
    private List<String> productSpecsButtonPaths;
    private List<String> productDescriptionButtonPaths;
    private List<String> productCookiesButtonPaths;
    private List<String> productOtherButtonPaths;
    private List<String> productCodePaths;
    private TimeStamp recordCreationTimeStamp;

    public CrawlerConfiguration() {
    }

    public CrawlerConfiguration(List<String> productNamePaths, List<String> productImagePaths, List<String> productPricePaths, List<String> productBrandPaths, List<String> productSpecsPaths, List<String> productDescriptionPaths, List<String> productSpecsButtonPaths, List<String> productDescriptionButtonPaths, List<String> productCookiesButtonPaths, List<String> productOtherButtonPaths, List<String> productCodePaths) {
        this.productNamePaths = productNamePaths;
        this.productImagePaths = productImagePaths;
        this.productPricePaths = productPricePaths;
        this.productBrandPaths = productBrandPaths;
        this.productSpecsPaths = productSpecsPaths;
        this.productDescriptionPaths = productDescriptionPaths;
        this.productSpecsButtonPaths = productSpecsButtonPaths;
        this.productDescriptionButtonPaths = productDescriptionButtonPaths;
        this.productCookiesButtonPaths = productCookiesButtonPaths;
        this.productOtherButtonPaths = productOtherButtonPaths;
        this.productCodePaths = productCodePaths;
    }

    public String getRetailerUuid() {
        return retailerUuid;
    }

    public void setRetailerUuid(String retailerUuid) {
        this.retailerUuid = retailerUuid;
    }

    public List<String> getProductCodePaths() {
        return productCodePaths;
    }

    public void setProductCodePaths(List<String> productCodePaths) {
        this.productCodePaths = productCodePaths;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public String getMainPageUrl() {
        return mainPageUrl;
    }

    public void setMainPageUrl(String mainPageUrl) {
        this.mainPageUrl = mainPageUrl;
    }

    public String getRetailerName() {
        return retailerName;
    }

    public void setRetailerName(String retailerName) {
        this.retailerName = retailerName;
    }

    public List<String> getProductNamePaths() {
        return productNamePaths;
    }

    public void setProductNamePaths(List<String> productNamePaths) {
        this.productNamePaths = productNamePaths;
    }

    public List<String> getProductImagePaths() {
        return productImagePaths;
    }

    public void setProductImagePaths(List<String> productImagePaths) {
        this.productImagePaths = productImagePaths;
    }

    public List<String> getProductPricePaths() {
        return productPricePaths;
    }

    public void setProductPricePaths(List<String> productPricePaths) {
        this.productPricePaths = productPricePaths;
    }

    public List<String> getProductBrandPaths() {
        return productBrandPaths;
    }

    public void setProductBrandPaths(List<String> productBrandPaths) {
        this.productBrandPaths = productBrandPaths;
    }

    public List<String> getProductSpecsPaths() {
        return productSpecsPaths;
    }

    public void setProductSpecsPaths(List<String> productSpecsPaths) {
        this.productSpecsPaths = productSpecsPaths;
    }

    public List<String> getProductDescriptionPaths() {
        return productDescriptionPaths;
    }

    public void setProductDescriptionPaths(List<String> productDescriptionPaths) {
        this.productDescriptionPaths = productDescriptionPaths;
    }

    public List<String> getProductSpecsButtonPaths() {
        return productSpecsButtonPaths;
    }

    public void setProductSpecsButtonPaths(List<String> productSpecsButtonPaths) {
        this.productSpecsButtonPaths = productSpecsButtonPaths;
    }

    public List<String> getProductDescriptionButtonPaths() {
        return productDescriptionButtonPaths;
    }

    public void setProductDescriptionButtonPaths(List<String> productDescriptionButtonPaths) {
        this.productDescriptionButtonPaths = productDescriptionButtonPaths;
    }

    public List<String> getProductCookiesButtonPaths() {
        return productCookiesButtonPaths;
    }

    public void setProductCookiesButtonPaths(List<String> productCookiesButtonPaths) {
        this.productCookiesButtonPaths = productCookiesButtonPaths;
    }

    public List<String> getProductOtherButtonPaths() {
        return productOtherButtonPaths;
    }

    public void setProductOtherButtonPaths(List<String> productOtherButtonPaths) {
        this.productOtherButtonPaths = productOtherButtonPaths;
    }

    public TimeStamp getRecordCreationTimeStamp() {
        return recordCreationTimeStamp;
    }

    public void setRecordCreationTimeStamp(TimeStamp recordCreationTimeStamp) {
        this.recordCreationTimeStamp = recordCreationTimeStamp;
    }

    @Override
    public String toString() {
        return "CrawlerConfiguration{" +
                "id='" + uuid + '\'' +
                ", retailerName='" + retailerName + '\'' +
                ", productNamePaths=" + productNamePaths +
                ", productImagePaths=" + productImagePaths +
                ", productPricePaths=" + productPricePaths +
                ", productBrandPaths=" + productBrandPaths +
                ", productSpecsPaths=" + productSpecsPaths +
                ", productDescriptionPaths=" + productDescriptionPaths +
                ", productSpecsButtonPaths=" + productSpecsButtonPaths +
                ", productDescriptionButtonPaths=" + productDescriptionButtonPaths +
                ", productCookiesButtonPaths=" + productCookiesButtonPaths +
                ", productOtherButtonPaths=" + productOtherButtonPaths +
                ", recordCreationTimeStamp=" + recordCreationTimeStamp +
                '}';
    }
}

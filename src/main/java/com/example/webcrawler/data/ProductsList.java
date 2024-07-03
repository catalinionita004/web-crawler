package com.example.webcrawler.data;

import java.util.List;

public class ProductsList {
    private List<Product> products;
    private String shortStoreName;

    public List<Product> getProducts() {
        return products;
    }

    public ProductsList() {
    }

    public ProductsList(List<Product> products, String shortStoreName) {
        this.products = products;
        this.shortStoreName = shortStoreName;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }

    public String getShortStoreName() {
        return shortStoreName;
    }

    public void setShortStoreName(String shortStoreName) {
        this.shortStoreName = shortStoreName;
    }
}

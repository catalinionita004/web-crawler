package com.example.webcrawler.data;


public class ProductSpecification {
    String header;
    String name;
    String value;

    public ProductSpecification() {
    }

    public ProductSpecification(String header, String name, String value) {
        this.header = header;
        this.name = name;
        this.value = value;
    }

     public String getHeader() {
        return header;
    }

    public void setHeader(String header) {
        this.header = header;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return "ProductSpecification{" +
                "header='" + header + '\'' +
                ", name='" + name + '\'' +
                ", value='" + value + '\'' +
                '}';
    }
}

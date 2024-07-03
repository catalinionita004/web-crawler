package com.example.webcrawler.services;

import com.example.webcrawler.data.Product;
import com.example.webcrawler.data.dtos.ProductDTO;
import com.example.webcrawler.data.ProductSpecification;
import com.example.webcrawler.data.dtos.ProductSpecificationDTO;
import org.springframework.stereotype.Component;

import static java.util.stream.Collectors.toList;

@Component
public class ProductMapper {
    public static ProductDTO toDTO(Product product) {
        return new ProductDTO(
                product.getUuid(),
                product.getName(),
                product.getImageUrl(),
                product.getPriceAmount(),
                product.getPriceCurrency(),
                product.getCategory(),
                product.getBrand(),
                product.getDescription(),
                product.getSpecifications().stream().map(ProductMapper::toDTO).collect(toList()),
                product.getRetailerName(),
                product.getRetailerUuid(),
                product.getCode()
        );
    }

    public static ProductSpecificationDTO toDTO(ProductSpecification productSpecification) {
        return new ProductSpecificationDTO(
                productSpecification.getHeader(),
                productSpecification.getName(),
                productSpecification.getValue()
        );
    }
}

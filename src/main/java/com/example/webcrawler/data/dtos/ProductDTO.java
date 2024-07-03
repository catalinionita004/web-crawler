package com.example.webcrawler.data.dtos;

import java.util.List;

public record ProductDTO(String uuid, String name, String imageUrl, double priceAmount, String priceCurrency,
                         String category, String brand, String description,
                         List<ProductSpecificationDTO> specifications, String retailerName, String retailerUuid, String code) {
}

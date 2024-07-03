package com.example.webcrawler.data.dtos;

import java.util.List;

public record SaveProductMetadataDTO(String retailerUuid, String category, List<String> urls) {
}

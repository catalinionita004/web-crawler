package com.example.webcrawler.data.dtos;

import java.util.List;

public record RequestTestDTO(CrawlerConfigurationDTO crawlerConfigurationDTO, List<String> urls) {
}

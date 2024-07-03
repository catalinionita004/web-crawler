package com.example.webcrawler.services;

import com.example.webcrawler.data.dtos.CrawlerConfigurationDTO;
import com.example.webcrawler.data.dtos.RetailerDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RetailerService {

    @Autowired
    BigQueryService bigQueryService;

    public List<RetailerDto> getRetailers() {
        return bigQueryService.getRetailers();
    }
}

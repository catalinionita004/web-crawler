package com.example.webcrawler.services;

import com.example.webcrawler.data.CrawlerConfiguration;
import com.example.webcrawler.data.dtos.CrawlerConfigurationDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class CrawlerConfigurationService {


    @Autowired
    CrawlerConfigurationMapper crawlerConfigurationMapper;

    @Autowired
    BigQueryService bigQueryService;

    @Autowired
    WebCrawlingService webCrawlingService;

    private static final Logger logger = LoggerFactory.getLogger(CrawlerConfigurationService.class);


    public void saveConfiguration(CrawlerConfigurationDTO crawlerConfigurationDTO){
        bigQueryService.saveCrawlerConfiguration(crawlerConfigurationMapper.fromDto(crawlerConfigurationDTO));
    }

    public CrawlerConfiguration getConfiguration(String retailerID) {
        return bigQueryService.getConfiguration(retailerID);
    }

    public void validateCrawlerConfiguration(CrawlerConfigurationDTO crawlerConfigurationDTO){
        webCrawlingService.validateCrawlerConfiguration(crawlerConfigurationMapper.fromDto(crawlerConfigurationDTO),"category","userManualUuid");
    }

}

package com.example.webcrawler.controllers;

import com.example.webcrawler.apis.CrawlerConfigurationControllerApi;
import com.example.webcrawler.data.dtos.CrawlerConfigurationDTO;
import com.example.webcrawler.services.CrawlerConfigurationMapper;
import com.example.webcrawler.services.CrawlerConfigurationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "api/v1/crawler")
@CrossOrigin
public class CrawlerConfigurationController implements CrawlerConfigurationControllerApi {

    @Autowired
    CrawlerConfigurationService crawlerConfigurationService;

    @Autowired
    CrawlerConfigurationMapper crawlerConfigurationMapper;

    @GetMapping("/getConfiguration")
    @ResponseBody
    public ResponseEntity<CrawlerConfigurationDTO> getCrawlerConfiguration(@RequestParam(required = true) String retailerUuid) {
        return new ResponseEntity<>(crawlerConfigurationMapper.toDto(crawlerConfigurationService.getConfiguration(retailerUuid)), HttpStatus.OK);
    }

//    @PostMapping("/saveConfiguration")
//    @ResponseBody
//    public ResponseEntity<Object> saveCrawlerConfiguration(@RequestBody CrawlerConfigurationDTO crawlerConfigurationDTO) {
//        crawlerConfigurationService.saveConfiguration(crawlerConfigurationDTO);
//        return new ResponseEntity<>(HttpStatus.CREATED);
//    }
}

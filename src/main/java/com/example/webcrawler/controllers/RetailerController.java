package com.example.webcrawler.controllers;

import com.example.webcrawler.apis.RetailerControllerApi;
import com.example.webcrawler.services.RetailerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "api/v1/retailer")
@CrossOrigin(origins = "*")
public class RetailerController implements RetailerControllerApi {

    @Autowired
    private RetailerService retailerService;

    @Override
    @GetMapping
    public ResponseEntity<?> getRetailers() {
        return new ResponseEntity<>(retailerService.getRetailers(), HttpStatus.OK);
    }
}

package com.example.webcrawler.controllers;

import com.example.webcrawler.apis.ProductControllerApi;
import com.example.webcrawler.data.dtos.ProductDTO;
import com.example.webcrawler.data.dtos.RequestTestDTO;
import com.example.webcrawler.data.dtos.SaveProductMetadataDTO;
import com.example.webcrawler.services.CrawlerConfigurationService;
import com.example.webcrawler.services.ProductMapper;
import com.example.webcrawler.services.ProductService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static java.util.stream.Collectors.toList;

@RestController
@RequestMapping(path = "api/v1/product")
@CrossOrigin(origins = "*")
public class ProductController implements ProductControllerApi {

    private static final Logger logger = LoggerFactory.getLogger(ProductController.class);
    @Autowired
    private ProductService productService;
    @Autowired
    private CrawlerConfigurationService crawlerConfigurationService;

    @PostMapping("/save")
    @ResponseBody
    public ResponseEntity<List<ProductDTO>> saveProductsFromUrls(@RequestBody SaveProductMetadataDTO saveProductMetadataDto) {
        logger.info("saveProductsFromUrls(): ENTER ->");
        return new ResponseEntity<>(productService.saveProductsFromUrlsAndManuals(saveProductMetadataDto).stream().map(ProductMapper::toDTO).collect(toList()), HttpStatus.OK);
    }

    @PostMapping("/save/test")
    public ResponseEntity<Object> saveTestProductsFromUrls(@RequestBody RequestTestDTO requestTestDTO) {
        logger.info("saveTestProductsFromUrls(): ENTER ->");

        crawlerConfigurationService.validateCrawlerConfiguration(requestTestDTO.crawlerConfigurationDTO());
        productService.saveTestProductsFromUrlsAndManuals(requestTestDTO);
        crawlerConfigurationService.saveConfiguration(requestTestDTO.crawlerConfigurationDTO());

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/filterBy")
    @ResponseBody
    public ResponseEntity<List<ProductDTO>> getProductBy(@RequestParam(required = false) String retailerUuid, @RequestParam(required = false) String category, @RequestParam(required = false) String brand, @RequestParam(required = false) String code) {
        return new ResponseEntity<>(productService.filterProductsBy(retailerUuid, category, brand, code).stream().map(ProductMapper::toDTO).collect(toList()), HttpStatus.OK);
    }

    @GetMapping("/id")
    @ResponseBody
    public ResponseEntity<ProductDTO> getProductById(@RequestParam(required = false) String id) {
        return new ResponseEntity<>(ProductMapper.toDTO(productService.getProductById(id)), HttpStatus.OK);
    }

    @GetMapping("/categories")
    @ResponseBody
    public ResponseEntity<List<String>> getCategories(@RequestParam(required = false) String retailerUuid) {
        return new ResponseEntity<>(productService.getCategories(retailerUuid), HttpStatus.OK);
    }

    @GetMapping("/brands")
    @ResponseBody
    public ResponseEntity<List<String>> getBrands(@RequestParam(required = false) String retailerUuid, @RequestParam(required = false) String category) {
        return new ResponseEntity<>(productService.getBrands(retailerUuid, category), HttpStatus.OK);
    }

    @GetMapping("/brand/exist")
    @ResponseBody
    public ResponseEntity<Boolean> existsBrand(@RequestParam(required = false) String retailerUuid, @RequestParam(required = false) String brand) {
        return new ResponseEntity<>(productService.existsBrand(retailerUuid, brand), HttpStatus.OK);
    }


}

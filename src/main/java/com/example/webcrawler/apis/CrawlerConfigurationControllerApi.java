package com.example.webcrawler.apis;

import com.example.webcrawler.data.dtos.CrawlerConfigurationDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;

@Tag(name = "Crawler Configuration", description = "the Crawler Configuration Api")
public interface CrawlerConfigurationControllerApi {
    @Operation(
            summary = "Gets configurations",
            description = "fetches all configuration entities based on retailer uuid")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "successful operation")
    })
    ResponseEntity<CrawlerConfigurationDTO> getCrawlerConfiguration(String retailerID);


//    @Operation(
//            summary = "Save configurations",
//            description = "save configuration")
//    @ApiResponses(value = {
//            @ApiResponse(responseCode = "200", description = "successful operation")
//    })
//    ResponseEntity<Object>  saveCrawlerConfiguration(CrawlerConfigurationDTO crawlerConfigurationDTO);


}

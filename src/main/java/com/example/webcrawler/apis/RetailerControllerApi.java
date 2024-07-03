package com.example.webcrawler.apis;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;

@Tag(name = "Retailer", description = "the retailer Api")
public interface RetailerControllerApi {
    @Operation(
            summary = "Gets retailers",
            description = "fetches all retailers"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "successful operation")
    })
    ResponseEntity<?> getRetailers();
}

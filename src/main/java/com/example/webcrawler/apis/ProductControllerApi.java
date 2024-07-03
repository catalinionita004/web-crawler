package com.example.webcrawler.apis;

import com.example.webcrawler.data.dtos.ProductDTO;
import com.example.webcrawler.data.dtos.RequestTestDTO;
import com.example.webcrawler.data.dtos.SaveProductMetadataDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;

import java.util.List;

@Tag(name = "Product Controller", description = "the Product Controller Api")
public interface ProductControllerApi {
    @Operation(
            summary = "Save products",
            description = "save products")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "successful operation")
    })
    ResponseEntity<List<ProductDTO>> saveProductsFromUrls(SaveProductMetadataDTO saveProductMetadataDto);

    @Operation(
            summary = "Save test products",
            description = "save test products")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "successful operation")
    })
    ResponseEntity<Object> saveTestProductsFromUrls(RequestTestDTO requestTestDTO);


    @Operation(
            summary = "Filter Products",
            description = "filter products by retailer uuid, brand, category or code")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "successful operation")
    })
    ResponseEntity<List<ProductDTO>> getProductBy(String retailerUuid, String category, String brand, String code);

    @Operation(
            summary = "Get Product",
            description = "get product by id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "successful operation")
    })
    ResponseEntity<ProductDTO> getProductById(String id);

    @Operation(
            summary = "Get categories",
            description = "get all categories for a retailer by providing retailer uuid")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "successful operation")
    })
    ResponseEntity<List<String>> getCategories(String retailerUuid);

    @Operation(
            summary = "Get brands",
            description = "get all brands for a retailer and category by providing retailer uuid and category")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "successful operation")
    })
    ResponseEntity<List<String>> getBrands(String retailerUuid, String category);

    @Operation(
            summary = "Check if a brand exists",
            description = "returns true if a brand exists or not for the provided retailer uuid")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "successful operation")
    })
    ResponseEntity<Boolean> existsBrand(String retailerUuid, String brand);
}

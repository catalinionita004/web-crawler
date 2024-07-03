//package com.example.webcrawler;
//
//import com.example.webcrawler.controllers.ProductController;
//import com.example.webcrawler.data.Product;
//import com.example.webcrawler.data.ProductSpecification;
//import com.example.webcrawler.services.ProductService;
//import com.fasterxml.jackson.databind.ObjectMapper;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.http.MediaType;
//import org.springframework.test.web.servlet.MockMvc;
//import org.springframework.test.web.servlet.ResultActions;
//import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
//import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
//import static org.mockito.BDDMockito.*;
//import org.mockito.Mockito;
//import org.springframework.boot.test.mock.mockito.MockBean;
//import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
//
//import java.util.List;
//
//import static org.assertj.core.api.Assertions.assertThat;
//import static org.mockito.Mockito.when;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
//
//@SpringBootTest
//@AutoConfigureMockMvc
//public class ProductControllerTest {
//    @Autowired
//    private ProductController productController;
//
//    @Autowired
//    private MockMvc mockMvc;
//
//    @MockBean
//    private ProductService productService;
//
//    @Autowired
//    private ObjectMapper objectMapper;
//
//    @Test
//    public void contextLoads() throws Exception {
//        assertThat(productController).isNotNull();
//    }
//
//    @Test
//    public void testGetProductById() throws Exception {
//        Product product = new Product();
//        ProductSpecification productSpecification = new ProductSpecification();
//        String id = "1";
//        String productName = "Xiaomi 13 pro";
//        String imageUrl = "imageUrl";
//        double price = 540.99;
//        String priceCurrency = "euro";
//        String category = "smartphone";
//        String brand = "Xiaomi";
//        String description = "Good phone";
//        String retailerName = "amazon";
//        List<ProductSpecification> specifications = List.of(productSpecification);
//
//        product.setId(id);
//        product.setProductName(productName);
//        product.setImageUrl(imageUrl);
//        product.setPrice(price);
//        product.setPriceCurrency(priceCurrency);
//        product.setCategory(category);
//        product.setBrand(brand);
//        product.setDescription(description);
//        product.setRetailerName(retailerName);
//        product.setSpecifications(specifications);
//
//        Mockito.when(productService.getProductById(id)).thenReturn(product);
//        ResultActions resultActions = mockMvc.perform(get("http://localhost:8080/api/v1/product/id")
//                        .param("id", "1")
//                        .contentType(MediaType.APPLICATION_JSON))
//                .andExpect(status().isOk())
//                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
//                .andExpect(jsonPath("$.productName").value("Xiaomi 13 pro"))
//                .andExpect(jsonPath("$.price").value(540.99));
//    }
//}

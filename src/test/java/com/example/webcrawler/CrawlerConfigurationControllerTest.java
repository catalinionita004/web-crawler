//package com.example.webcrawler;
//
//import com.example.webcrawler.controllers.CrawlerConfigurationController;
//import com.example.webcrawler.data.CrawlerConfiguration;
//import com.example.webcrawler.data.dtos.CrawlerConfigurationDTO;
//import com.example.webcrawler.exceptions.NoResults;
//import com.example.webcrawler.exceptions.NotSavedException;
//import com.example.webcrawler.services.CrawlerConfigurationService;
//import com.fasterxml.jackson.databind.ObjectMapper;
//import org.junit.jupiter.api.Test;
//import org.mockito.Mockito;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.boot.test.mock.mockito.MockBean;
//import org.springframework.http.MediaType;
//import org.springframework.test.web.servlet.MockMvc;
//import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
//
//import static org.assertj.core.api.Assertions.assertThat;
//import static org.junit.jupiter.api.Assertions.assertNotNull;
//import static org.mockito.ArgumentMatchers.any;
//import static org.mockito.ArgumentMatchers.eq;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
//
//import java.util.List;
//
//@SpringBootTest
//@AutoConfigureMockMvc
//public class CrawlerConfigurationControllerTest {
//    @Autowired
//    private CrawlerConfigurationController crawlerConfigurationController;
//
//    @Autowired
//    private MockMvc mockMvc;
//
//    @MockBean
//    private CrawlerConfigurationService crawlerConfigurationService;
//
//    @Autowired
//    private ObjectMapper objectMapper;
//
//
//    @Test
//    public void contextLoads() throws Exception {
//        assertThat(crawlerConfigurationController).isNotNull();
//    }
//
//    @Test
//    public void testPost() throws Exception {
//        String mainPageUrl = "amazon.com";
//        String retailerName = "amazon";
//        List<String> namePaths = List.of("nameXpath");
//        List<String> imagePaths = List.of("imageXpath");
//        List<String> pricePaths = List.of("priceXpath");
//        List<String> brandPaths = List.of("brandXpath");
//        List<String> specsPaths = List.of("specificationsXpath");
//        List<String> descriptionPaths = List.of("descriptionXpath");
//        List<String> descriptionButtonPaths = List.of("descriptionButtonXpath");
//        List<String> specsButtonPaths = List.of("specsButtonXpath");
//        List<String> cookiesButtonPaths = List.of("cookiesButtonXPath");
//        List<String> otherButtonPaths = List.of("otherButtonXPath");
//
//        CrawlerConfigurationDTO crawlerConfigurationDTO = new CrawlerConfigurationDTO(mainPageUrl, retailerName, namePaths, imagePaths, pricePaths, brandPaths, specsPaths, descriptionPaths, specsButtonPaths, descriptionButtonPaths, cookiesButtonPaths, otherButtonPaths);
//        CrawlerConfigurationDTO crawlerConfigurationDTOExpectedException = new CrawlerConfigurationDTO();
//
//        String requestBody = objectMapper.writeValueAsString(crawlerConfigurationDTO);
//        String requestBodyExpectedException = objectMapper.writeValueAsString(crawlerConfigurationDTOExpectedException);
//
//        Mockito.doNothing().when(crawlerConfigurationService).saveConfiguration(Mockito.any(CrawlerConfigurationDTO.class));
//
//        mockMvc.perform(MockMvcRequestBuilders.post("/api/v1/crawler/saveConfiguration")
//                        .content(requestBody).contentType(MediaType.APPLICATION_JSON)
//                )
//                .andExpect(status().isCreated());
//
//        Mockito.doThrow(new NotSavedException("configuration didn't save")).when(crawlerConfigurationService).saveConfiguration(Mockito.any(CrawlerConfigurationDTO.class));
//
//        mockMvc.perform(MockMvcRequestBuilders.post("/api/v1/crawler/saveConfiguration")
//                        .content(requestBodyExpectedException).contentType(MediaType.APPLICATION_JSON)
//                )
//                .andExpect(status().isInternalServerError());
//    }
//
//    @Test
//    public void testGet() throws Exception {
//        CrawlerConfiguration crawlerConfiguration = new CrawlerConfiguration();
//
//        String retailerName= "retailerName";
//        Mockito.when(crawlerConfigurationService.getConfiguration(retailerName)).thenReturn(crawlerConfiguration);
//
//        mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/crawler/getConfiguration")
//                        .param("retailerName",retailerName)
//                )
//                .andExpect(status().isOk());
//
//        String retailerNameInvalid = "retailerNameThatDoesntExist";
//        String errorMessage = "retailerName" + retailerNameInvalid + "doesn't exist";
//        Mockito.when(crawlerConfigurationService.getConfiguration(retailerNameInvalid)).thenThrow(new NoResults(errorMessage));
//
//        mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/crawler/getConfiguration")
//                        .param("retailerName",retailerNameInvalid)
//                )
//                .andExpect(status().isInternalServerError())
//                .andExpect(jsonPath("$.errorCode").value("no results"))
//                .andExpect(jsonPath("$.message").value(errorMessage));
//
//    }
//}

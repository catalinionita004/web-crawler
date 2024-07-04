package com.example.webcrawler;


import com.google.cloud.bigquery.BigQuery;
import com.google.cloud.bigquery.BigQueryOptions;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.servers.Server;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import com.google.cloud.storage.*;
@OpenAPIDefinition(
        servers = {
                @Server(url = "/", description = "Default Server URL")
        }
)
@SpringBootApplication
public class WebCrawlerApplication {
    @Bean
    BigQuery bigQuery() {
        return BigQueryOptions.getDefaultInstance().getService();
    }

    public static void main(String[] args) {

        SpringApplication.run(WebCrawlerApplication.class, args);

    }

}

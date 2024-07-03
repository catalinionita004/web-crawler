package com.example.webcrawler;


import com.google.cloud.bigquery.BigQuery;
import com.google.cloud.bigquery.BigQueryOptions;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import com.google.cloud.storage.*;

@SpringBootApplication
public class WebCrawlerApplication {
    @Bean
    Storage storage() {
        return StorageOptions.getDefaultInstance().getService();
    }

    @Bean
    BigQuery bigQuery() {
        return BigQueryOptions.getDefaultInstance().getService();
    }

    public static void main(String[] args) {

        SpringApplication.run(WebCrawlerApplication.class, args);

    }

}

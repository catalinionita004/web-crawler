//package com.example.webcrawler.repositories;
//
//import com.example.webcrawler.data.CrawlerConfiguration;
//import org.springframework.data.jpa.repository.JpaRepository;
//import org.springframework.data.jpa.repository.Modifying;
//import org.springframework.data.jpa.repository.Query;
//import org.springframework.data.repository.query.Param;
//import org.springframework.stereotype.Repository;
//
//import java.util.List;
//
//@Repository
//public interface CrawlerConfigurationRepository extends JpaRepository <CrawlerConfiguration, Long> {
//    List<CrawlerConfiguration> findByretailerName (String retailerName);
//
//
//    @Query(value = "SELECT id FROM crawler_configuration where store_short_name = :retailerName and name = :name", nativeQuery = true)
//    Long findIdByretailerNameAndName(@Param("retailerName") String retailerName, @Param("name") String name);
//
//
//    @Query(value = "SELECT value FROM crawler_configuration where store_short_name = :retailerName and name = :name", nativeQuery = true)
//    String findValueByretailerNameAndName(@Param("retailerName") String retailerName, @Param("name") String name);
//
//
//
//}

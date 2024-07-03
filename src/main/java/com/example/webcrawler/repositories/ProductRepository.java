//package com.example.webcrawler.repositories;
//
//import com.example.webcrawler.data.Product;
//import org.springframework.data.jpa.repository.JpaRepository;
//import org.springframework.data.jpa.repository.Query;
//import org.springframework.data.repository.query.Param;
//import org.springframework.stereotype.Repository;
//
//import java.util.List;
//
//@Repository
//public interface ProductRepository extends JpaRepository<Product, Long> {
//    public final static String FILTER_PRODUCTS_BY = "SELECT * FROM product where brand = :brand or store_short_name = :retailerName or category = :category";
//
//List<Product> findByProductNameAndRetailerName(String productName, String retailerName);
//
//@Query(value = FILTER_PRODUCTS_BY, nativeQuery = true)
//List<Product> findByFilters(@Param("retailerName") String retailerName, @Param("brand") String brand, @Param("category") String category);
//}

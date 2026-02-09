package com.example.demo.repo;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.math.BigDecimal;
import java.util.List;

//import org.springframework.data.domain.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.demo.entity.Product;

import jakarta.transaction.Transactional;

public interface ProductRepository extends JpaRepository<Product, Long> {

	@Query("""
			    SELECT p FROM Product p
			    WHERE p.active = true
			      AND (LOWER(p.name) LIKE LOWER(CONCAT('%', :q, '%'))
			       OR LOWER(p.sku)  LIKE LOWER(CONCAT('%', :q, '%')))
			""")
	Page<Product> searchActive(@Param("q") String q, Pageable pageable);

	@Query("""
			    SELECT p FROM Product p
			    WHERE p.category.name = :catName
			""")
	List<Product> findByCategoryName(@Param("catName") String catName);

	@Query("""
			    SELECT p FROM Product p
			    WHERE p.active = true
			      AND p.price BETWEEN :min AND :max
			""")
	Page<Product> activeByPriceRange(@Param("min") BigDecimal min, @Param("max") BigDecimal max, Pageable pageable);

    @Modifying
    @Transactional
    @Query(value = "UPDATE products SET stock = :stock WHERE id = :id", nativeQuery = true)
    int updateStockNative(@Param("id") Long id, @Param("stock") int stock);

	
    @Query(value = "SELECT * FROM products WHERE stock <= :threshold ORDER BY stock ASC", nativeQuery = true)
    List<Product> lowStockNative(@Param("threshold") int threshold);
    
    @Query(value = """
    	       SELECT p.*
    	       FROM products p
    	       JOIN order_items oi ON oi.product_id = p.id
    	       GROUP BY p.id
    	       ORDER BY SUM(oi.quantity) DESC
    	       LIMIT :limit
    	    """, nativeQuery = true)
    	    List<Product> topSellingNative(@Param("limit") int limit);
}
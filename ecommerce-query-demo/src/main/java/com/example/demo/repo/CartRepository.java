package com.example.demo.repo;

import java.math.BigDecimal;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.demo.entity.Cart;

public interface CartRepository extends JpaRepository<Cart, Long> {

    Optional<Cart> findByCustomerId(Long customerId);

    // ✅ Native: Cart total = SUM(qty * price)
    @Query(value = """
       SELECT COALESCE(SUM(ci.quantity * p.price), 0)
       FROM cart_items ci
       JOIN products p ON p.id = ci.product_id
       WHERE ci.cart_id = :cartId
    """, nativeQuery = true)
    BigDecimal cartTotalNative(@Param("cartId") Long cartId);
}
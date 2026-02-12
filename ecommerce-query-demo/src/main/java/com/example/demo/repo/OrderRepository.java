package com.example.demo.repo;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import java.time.LocalDateTime;
import java.util.List;

//import org.springframework.data.domain.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.demo.entity.OrderEntity;

import jakarta.transaction.Transactional;

public interface OrderRepository extends JpaRepository<OrderEntity, Long> {

    
    @Query("""
      SELECT o FROM OrderEntity o
      WHERE o.customer.id = :customerId
      ORDER BY o.orderDate DESC
    """)
    Page<OrderEntity> ordersForCustomer(@Param("customerId") Long customerId, Pageable pageable);

 
    @Query("""
      SELECT o FROM OrderEntity o
      WHERE o.status = :status
        AND o.orderDate BETWEEN :from AND :to
      ORDER BY o.orderDate DESC
    """)
    List<OrderEntity> byStatusBetween(@Param("status") String status,
                                     @Param("from") LocalDateTime from,
                                     @Param("to") LocalDateTime to);

    
    @Modifying
    @Transactional
    @Query(value = "UPDATE orders SET status = :status WHERE id = :orderId", nativeQuery = true)
    int updateStatusNative(@Param("orderId") Long orderId, @Param("status") String status);
}
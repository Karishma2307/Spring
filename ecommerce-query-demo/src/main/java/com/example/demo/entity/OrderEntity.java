package com.example.demo.entity;

import jakarta.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name="orders")
public class OrderEntity {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="customer_id", nullable=false)
    private Customer customer;

    @Column(nullable=false)
    private String status; 

    private LocalDateTime orderDate = LocalDateTime.now();

    @Column(nullable=false)
    private BigDecimal totalAmount;

    public OrderEntity() {}
    public OrderEntity(Customer customer, String status, BigDecimal totalAmount) {
        this.customer = customer;
        this.status = status;
        this.totalAmount = totalAmount;
    }

    public Long getId() { return id; }
    public Customer getCustomer() { return customer; }
    public String getStatus() { return status; }
    public LocalDateTime getOrderDate() { return orderDate; }
    public BigDecimal getTotalAmount() { return totalAmount; }

    public void setCustomer(Customer customer) { this.customer = customer; }
    public void setStatus(String status) { this.status = status; }
    public void setTotalAmount(BigDecimal totalAmount) { this.totalAmount = totalAmount; }
}
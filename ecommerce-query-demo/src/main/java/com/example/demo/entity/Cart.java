package com.example.demo.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name="carts")
public class Cart {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="customer_id", nullable=false, unique=true)
    private Customer customer;

    private LocalDateTime createdAt = LocalDateTime.now();

    public Cart() {}
    public Cart(Customer customer) {
        this.customer = customer;
    }

    public Long getId() { return id; }
    public Customer getCustomer() { return customer; }
    public LocalDateTime getCreatedAt() { return createdAt; }

    public void setCustomer(Customer customer) { this.customer = customer; }
}
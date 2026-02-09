package com.example.demo.entity;


import jakarta.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "products", indexes = {
        @Index(name="idx_products_name", columnList = "name"),
        @Index(name="idx_products_sku", columnList = "sku")
})
public class Product {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable=false)
    private String name;

    @Column(nullable=false, unique=true)
    private String sku;

    @Column(nullable=false)
    private BigDecimal price;

    @Column(nullable=false)
    private int stock;

    private boolean active = true;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="category_id", nullable=false)
    private Category category;

    public Product() {}

    public Product(String name, String sku, BigDecimal price, int stock, boolean active, Category category) {
        this.name = name;
        this.sku = sku;
        this.price = price;
        this.stock = stock;
        this.active = active;
        this.category = category;
    }

    public Long getId() { return id; }
    public String getName() { return name; }
    public String getSku() { return sku; }
    public BigDecimal getPrice() { return price; }
    public int getStock() { return stock; }
    public boolean isActive() { return active; }
    public Category getCategory() { return category; }

    public void setName(String name) { this.name = name; }
    public void setSku(String sku) { this.sku = sku; }
    public void setPrice(BigDecimal price) { this.price = price; }
    public void setStock(int stock) { this.stock = stock; }
    public void setActive(boolean active) { this.active = active; }
    public void setCategory(Category category) { this.category = category; }
}
package com.example.demo.entity;



import jakarta.persistence.*;
import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "product")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private BigDecimal price;

    // Many-to-Many using Join Table
    @ManyToMany
    @JoinTable(
            name = "product_category", // Join table name
            joinColumns = @JoinColumn(name = "product_id"), // FK to product table
            inverseJoinColumns = @JoinColumn(name = "category_id") // FK to category table
    )
    private Set<Category> categories = new HashSet<>();

    public Product() { }

    public Product(String name, BigDecimal price) {
        this.name = name;
        this.price = price;
    }

    // helper method (recommended)
    public void addCategory(Category category) {
        this.categories.add(category);
    }

    // Getters/Setters
    public Long getId() { return id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public BigDecimal getPrice() { return price; }
    public void setPrice(BigDecimal price) { this.price = price; }

    public Set<Category> getCategories() { return categories; }
    public void setCategories(Set<Category> categories) { this.categories = categories; }
}
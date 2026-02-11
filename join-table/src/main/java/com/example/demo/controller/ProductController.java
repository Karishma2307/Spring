package com.example.demo.controller;

import com.example.demo.entity.Product;
import com.example.demo.repo.ProductRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {

    private final ProductRepository productRepo;

    public ProductController(ProductRepository productRepo) {
        this.productRepo = productRepo;
    }

    // GET http://localhost:8080/products
    @GetMapping
    public List<Product> getAll() {
        return productRepo.findAll();
    }

    // GET http://localhost:8080/products/1
    @GetMapping("/{id}")
    public Product getOne(@PathVariable Long id) {
        return productRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Product not found: " + id));
    }
}
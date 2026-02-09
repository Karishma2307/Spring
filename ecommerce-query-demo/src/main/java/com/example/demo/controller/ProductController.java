package com.example.demo.controller;

import com.example.demo.dto.UpdateStockRequest;
import com.example.demo.entity.Product;
import com.example.demo.repo.ProductRepository;
import jakarta.validation.Valid;
//import org.springframework.data.domain.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import java.math.BigDecimal;
import java.util.List;

@RestController
@RequestMapping("/api/products")
public class ProductController {

    private final ProductRepository repo;

    public ProductController(ProductRepository repo) {
        this.repo = repo;
    }

    // ✅ Basic CRUD
    @GetMapping("/{id}")
    public Product get(@PathVariable Long id) {
        return repo.findById(id).orElseThrow(() -> new RuntimeException("Product not found"));
    }

    @PostMapping
    public Product create(@RequestBody Product p) {
        return repo.save(p);
    }

    @PutMapping("/{id}")
    public Product update(@PathVariable Long id, @RequestBody Product p) {
        Product db = repo.findById(id).orElseThrow(() -> new RuntimeException("Product not found"));
        db.setName(p.getName());
        db.setSku(p.getSku());
        db.setPrice(p.getPrice());
        db.setStock(p.getStock());
        db.setActive(p.isActive());
        db.setCategory(p.getCategory());
        return repo.save(db);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        repo.deleteById(id);
    }

    // ✅ Pagination + Sorting (built-in Spring Data feature)
    // Example: /api/products?page=0&size=10&sort=price,desc
    @GetMapping
    public Page<Product> list(Pageable pageable) {
        return repo.findAll(pageable);
    }

    // ✅ JPQL @Query search + pageable
    // /api/products/search?q=phone&page=0&size=5&sort=name,asc
    @GetMapping("/search")
    public Page<Product> search(@RequestParam String q, Pageable pageable) {
        return repo.searchActive(q, pageable);
    }

    // ✅ JPQL price range
    @GetMapping("/price-range")
    public Page<Product> priceRange(@RequestParam BigDecimal min,
                                    @RequestParam BigDecimal max,
                                    org.springframework.data.domain.Pageable pageable) {
        return repo.activeByPriceRange(min, max, pageable);
    }

    // ✅ Native: low stock
    @GetMapping("/low-stock")
    public List<Product> lowStock(@RequestParam(defaultValue = "5") int threshold) {
        return repo.lowStockNative(threshold);
    }

    // ✅ Native: update stock (fast update)
    @PutMapping("/{id}/stock")
    public String updateStock(@PathVariable Long id, @Valid @RequestBody UpdateStockRequest req) {
        int rows = repo.updateStockNative(id, req.stock());
        return "Updated rows: " + rows;
    }

    // ✅ Native: top-selling
    @GetMapping("/top-selling")
    public List<Product> topSelling(@RequestParam(defaultValue = "5") int limit) {
        return repo.topSellingNative(limit);
    }
}
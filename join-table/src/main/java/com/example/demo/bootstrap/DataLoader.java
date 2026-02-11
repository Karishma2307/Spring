package com.example.demo.bootstrap;



import com.example.demo.entity.Category;
import com.example.demo.entity.Product;
import com.example.demo.repo.CategoryRepository;
import com.example.demo.repo.ProductRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
public class DataLoader implements CommandLineRunner {

    private final ProductRepository productRepo;
    private final CategoryRepository categoryRepo;

    public DataLoader(ProductRepository productRepo, CategoryRepository categoryRepo) {
        this.productRepo = productRepo;
        this.categoryRepo = categoryRepo;
    }

    @Override
    public void run(String... args) {

        // 1) Create Categories
        Category electronics = categoryRepo.save(new Category("Electronics"));
        Category appliances   = categoryRepo.save(new Category("Home Appliances"));
        Category books        = categoryRepo.save(new Category("Books"));

        // 2) Create Products
        Product iphone = new Product("iPhone 15", new BigDecimal("79999.00"));
        iphone.addCategory(electronics);

        Product tv = new Product("Samsung TV", new BigDecimal("45999.00"));
        tv.addCategory(electronics);
        tv.addCategory(appliances);

        Product hpBooks = new Product("Harry Potter Set", new BigDecimal("2999.00"));
        hpBooks.addCategory(books);

        // 3) Save Products (Hibernate will insert join table rows automatically)
        productRepo.save(iphone);
        productRepo.save(tv);
        productRepo.save(hpBooks);
    }
}
package com.example.demo.config;

import com.example.demo.entity.*;
import com.example.demo.repo.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.*;

@Component
public class DataSeeder implements CommandLineRunner {

    private final CategoryRepository categoryRepo;
    private final ProductRepository productRepo;
    private final CustomerRepository customerRepo;
    private final CartRepository cartRepo;
    private final CartItemRepository cartItemRepo;
    private final OrderRepository orderRepo;
    private final OrderItemRepository orderItemRepo;

    public DataSeeder(CategoryRepository categoryRepo, ProductRepository productRepo,
                      CustomerRepository customerRepo, CartRepository cartRepo,
                      CartItemRepository cartItemRepo, OrderRepository orderRepo,
                      OrderItemRepository orderItemRepo) {
        this.categoryRepo = categoryRepo;
        this.productRepo = productRepo;
        this.customerRepo = customerRepo;
        this.cartRepo = cartRepo;
        this.cartItemRepo = cartItemRepo;
        this.orderRepo = orderRepo;
        this.orderItemRepo = orderItemRepo;
    }

    @Override
    public void run(String... args) {

        long catCount = categoryRepo.count();
        System.out.println(" DataSeeder started. categories count = " + catCount);

        if (catCount > 0) {
            System.out.println(" Seeding skipped (data already exists).");
            return; 
        }

       
        List<Category> categories = new ArrayList<>();
        for (int i = 1; i <= 30; i++) {
            categories.add(new Category("Category-" + i, true));
        }
        categoryRepo.saveAll(categories);

       
        List<Product> products = new ArrayList<>();
        int sku = 1000;
        Random r = new Random();

        for (Category c : categories) {
            for (int j = 1; j <= 2; j++) {
                products.add(new Product(
                        c.getName() + "-Product-" + j,
                        "SKU-" + (sku++),
                        BigDecimal.valueOf(100 + r.nextInt(900)),
                        5 + r.nextInt(50),
                        true,
                        c
                ));
            }
        }
        productRepo.saveAll(products);

        
        List<Customer> customers = new ArrayList<>();
        for (int i = 1; i <= 30; i++) {
            customers.add(new Customer("Customer-" + i, "cust" + i + "@mail.com"));
        }
        customerRepo.saveAll(customers);

        
        for (Customer cust : customers) {
            Cart cart = cartRepo.save(new Cart(cust));

            for (int k = 0; k < 2; k++) {
                Product p = products.get(r.nextInt(products.size()));
                cartItemRepo.save(new CartItem(cart, p, 1 + r.nextInt(3)));
            }
        }

        
        for (int i = 0; i < 30; i++) {
            Customer cust = customers.get(i);

            OrderEntity order = orderRepo.save(
                    new OrderEntity(cust, "PAID", BigDecimal.valueOf(500 + r.nextInt(2000)))
            );

            for (int k = 0; k < 2; k++) {
                Product p = products.get(r.nextInt(products.size()));
                int qty = 1 + r.nextInt(3);
                BigDecimal line = p.getPrice().multiply(BigDecimal.valueOf(qty));
                orderItemRepo.save(new OrderItem(order, p, qty, p.getPrice(), line));
            }
        }

        System.out.println(" Seed completed: 30+ records per table (categories, customers, carts, orders) and 60+ items.");
    }
}

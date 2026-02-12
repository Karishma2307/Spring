package com.example.demo.service;

import com.example.demo.entity.*;
import com.example.demo.repo.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;

@Service
public class OrderService {

    private final CartRepository cartRepo;
    private final CartItemRepository cartItemRepo;
    private final OrderRepository orderRepo;
    private final OrderItemRepository orderItemRepo;
    private final ProductRepository productRepo;

    public OrderService(CartRepository cartRepo, CartItemRepository cartItemRepo,
                        OrderRepository orderRepo, OrderItemRepository orderItemRepo,
                        ProductRepository productRepo) {
        this.cartRepo = cartRepo;
        this.cartItemRepo = cartItemRepo;
        this.orderRepo = orderRepo;
        this.orderItemRepo = orderItemRepo;
        this.productRepo = productRepo;
    }

    @Transactional
    public OrderEntity placeOrder(Long customerId) {
        Cart cart = cartRepo.findByCustomerId(customerId)
                .orElseThrow(() -> new RuntimeException("Cart not found for customer: " + customerId));

        List<CartItem> items = cartItemRepo.findAll().stream()
                .filter(ci -> ci.getCart().getId().equals(cart.getId()))
                .toList();

        if (items.isEmpty()) throw new RuntimeException("Cart is empty");

        BigDecimal total = cartRepo.cartTotalNative(cart.getId());
        OrderEntity order = orderRepo.save(new OrderEntity(cart.getCustomer(), "CREATED", total));

        for (CartItem ci : items) {
            Product p = ci.getProduct();
            if (p.getStock() < ci.getQuantity()) throw new RuntimeException("Stock changed. Try again.");

            
            productRepo.updateStockNative(p.getId(), p.getStock() - ci.getQuantity());

            BigDecimal line = p.getPrice().multiply(BigDecimal.valueOf(ci.getQuantity()));
            orderItemRepo.save(new OrderItem(order, p, ci.getQuantity(), p.getPrice(), line));
        }

        
        cartItemRepo.deleteByCartId(cart.getId());
        return order;
    }
}
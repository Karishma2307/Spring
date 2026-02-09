package com.example.demo.service;

import com.example.demo.entity.*;
import com.example.demo.repo.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;

@Service
public class CartService {

    private final CartRepository cartRepo;
    private final CartItemRepository cartItemRepo;
    private final CustomerRepository customerRepo;
    private final ProductRepository productRepo;

    public CartService(CartRepository cartRepo, CartItemRepository cartItemRepo,
                       CustomerRepository customerRepo, ProductRepository productRepo) {
        this.cartRepo = cartRepo;
        this.cartItemRepo = cartItemRepo;
        this.customerRepo = customerRepo;
        this.productRepo = productRepo;
    }

    @Transactional
    public Cart getOrCreateCart(Long customerId) {
        return cartRepo.findByCustomerId(customerId).orElseGet(() -> {
            Customer c = customerRepo.findById(customerId)
                    .orElseThrow(() -> new RuntimeException("Customer not found: " + customerId));
            return cartRepo.save(new Cart(c));
        });
    }

    @Transactional
    public CartItem addToCart(Long customerId, Long productId, int qty) {
        Cart cart = getOrCreateCart(customerId);
        Product p = productRepo.findById(productId)
                .orElseThrow(() -> new RuntimeException("Product not found: " + productId));

        if (!p.isActive()) throw new RuntimeException("Product is inactive");
        if (p.getStock() < qty) throw new RuntimeException("Insufficient stock");

        return cartItemRepo.findByCartIdAndProductId(cart.getId(), productId)
                .map(existing -> {
                    existing.setQuantity(existing.getQuantity() + qty);
                    return cartItemRepo.save(existing);
                })
                .orElseGet(() -> cartItemRepo.save(new CartItem(cart, p, qty)));
    }

    public BigDecimal cartTotal(Long customerId) {
        Cart cart = getOrCreateCart(customerId);
        return cartRepo.cartTotalNative(cart.getId());
    }

    @Transactional
    public void clearCart(Long customerId) {
        Cart cart = getOrCreateCart(customerId);
        cartItemRepo.deleteByCartId(cart.getId());
    }
}
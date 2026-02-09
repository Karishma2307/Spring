package com.example.demo.controller;

import com.example.demo.dto.AddToCartRequest;
import com.example.demo.service.CartService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;

@RestController
@RequestMapping("/api/cart")
public class CartController {

    private final CartService service;

    public CartController(CartService service) {
        this.service = service;
    }

    @PostMapping("/add")
    public Object add(@Valid @RequestBody AddToCartRequest req) {
        return service.addToCart(req.customerId(), req.productId(), req.quantity());
    }

    @GetMapping("/{customerId}/total")
    public BigDecimal total(@PathVariable Long customerId) {
        return service.cartTotal(customerId);
    }

    @DeleteMapping("/{customerId}/clear")
    public String clear(@PathVariable Long customerId) {
        service.clearCart(customerId);
        return "Cart cleared";
    }
}
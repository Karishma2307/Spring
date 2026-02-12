package com.example.demo.controller;



import com.example.demo.dto.CreateOrderRequest;
import com.example.demo.entity.OrderEntity;
import com.example.demo.repo.OrderRepository;
import com.example.demo.service.OrderService;
import jakarta.validation.Valid;
import org.springframework.data.domain.*;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/orders")
public class OrderController {

    private final OrderService service;
    private final OrderRepository repo;

    public OrderController(OrderService service, OrderRepository repo) {
        this.service = service;
        this.repo = repo;
    }

    
    @PostMapping("/place")
    public OrderEntity place(@Valid @RequestBody CreateOrderRequest req) {
        return service.placeOrder(req.customerId());
    }

    
    @GetMapping("/customer/{customerId}")
    public Page<OrderEntity> history(@PathVariable Long customerId, Pageable pageable) {
        return repo.ordersForCustomer(customerId, pageable);
    }

   
    @PutMapping("/{orderId}/status")
    public String updateStatus(@PathVariable Long orderId, @RequestParam String status) {
        int rows = repo.updateStatusNative(orderId, status);
        return "Updated rows: " + rows;
    }
}
package com.laundry.laundry_system.controller;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.laundry.laundry_system.dto.CreateOrderRequest;
import com.laundry.laundry_system.dto.OrderResponse;
import com.laundry.laundry_system.model.OrderStatus;
import com.laundry.laundry_system.service.OrderService;

@RestController
@RequestMapping("/orders")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @PostMapping
    public String createOrder(@RequestBody CreateOrderRequest request) {
        String userEmail = SecurityContextHolder.getContext().getAuthentication().getName();
        orderService.createOrder(request, userEmail);
        return "Order created successfully";
    }

    @GetMapping("/{id}")
    public OrderResponse getOrderById(@PathVariable UUID id) {
        return orderService.getOrderById(id);
    }

    @GetMapping("/user/{userId}")
    public List<OrderResponse> getOrdersByUser(@PathVariable UUID userId) {
        return orderService.getOrdersByUser(userId);
    }

    @PutMapping("/{id}/status")
    public String updateOrderStatus(@PathVariable UUID id, @RequestParam OrderStatus status) {
        orderService.updateOrderStatus(id, status);
        return "Order status updated successfully";
    }
}
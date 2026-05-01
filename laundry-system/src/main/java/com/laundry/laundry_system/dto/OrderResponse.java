package com.laundry.laundry_system.dto;

import java.time.LocalDateTime;
import java.util.UUID;

public class OrderResponse {
    private UUID id;
    private String customerName;
    private double weight;
    private double price;
    private LocalDateTime createdAt;
    private LocalDateTime completedAt;
    private String status;

    public OrderResponse(UUID id, String customerName, double weight, double price, LocalDateTime createdAt, LocalDateTime completedAt, String status) {
        this.id = id;
        this.customerName = customerName;
        this.weight = weight;
        this.price = price;
        this.createdAt = createdAt;
        this.completedAt = completedAt;
        this.status = status;
    }

    // Getters  
    public UUID getId() {
        return id;
    }   
    public String getCustomerName() {
        return customerName;
    }
    public double getWeight() {
        return weight;  
    }
    public double getPrice() {
        return price;
    }
    public LocalDateTime getCreatedAt() {
        return createdAt;
    }
    public LocalDateTime getCompletedAt() {
        return completedAt;
    }
    public String getStatus() {
        return status;
    }

}




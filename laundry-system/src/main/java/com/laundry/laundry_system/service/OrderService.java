package com.laundry.laundry_system.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.laundry.laundry_system.dto.CreateOrderRequest;
import com.laundry.laundry_system.dto.OrderResponse;
import com.laundry.laundry_system.exception.OrderNotFoundException;
import com.laundry.laundry_system.model.Order;
import com.laundry.laundry_system.model.OrderStatus;
import com.laundry.laundry_system.model.User;
import com.laundry.laundry_system.repository.OrderRepository;
import com.laundry.laundry_system.repository.UserRepository;

@Service
public class OrderService {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private UserRepository userRepository;

    public void createOrder(CreateOrderRequest request, String userEmail) {
        User user = userRepository.findByEmail(userEmail)
            .orElseThrow(() -> new OrderNotFoundException("User not found"));

        Order order = new Order();
        order.setUser(user);
        order.setWeight(request.getWeight());
        order.setStatus(OrderStatus.RECEIVED);
        order.setCreatedAt(LocalDateTime.now());
        order.setPrice(calculatePrice(request.getWeight()));

        orderRepository.save(order);
    }

    private double calculatePrice(double weight) {
        return weight * 5.0;
    }

    public OrderResponse getOrderById(UUID orderId) {
        Order order = orderRepository.findById(orderId)
            .orElseThrow(() -> new OrderNotFoundException("Order not found"));
        return convertToResponse(order);
    }

    public List<OrderResponse> getOrdersByUser(UUID userId) {
        List<Order> orders = orderRepository.findByUserId(userId);
        return orders.stream()
            .map(order -> convertToResponse(order))
            .collect(Collectors.toList());
    }

    public void updateOrderStatus(UUID orderId, OrderStatus status) {
        Order order = orderRepository.findById(orderId)
            .orElseThrow(() -> new OrderNotFoundException("Order not found"));
        order.setStatus(status);
        orderRepository.save(order);
    }

    private OrderResponse convertToResponse(Order order) {
        return new OrderResponse(
            order.getId(),
            order.getUser().getFirstName() + " " + order.getUser().getLastName(),
            order.getWeight(),
            order.getPrice(),
            order.getCreatedAt(),
            order.getCompletedAt(),
            order.getStatus().name()
        );
    }
}

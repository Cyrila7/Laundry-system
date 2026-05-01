package com.laundry.laundry_system.repository;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.laundry.laundry_system.model.Order;
import com.laundry.laundry_system.model.OrderStatus;

public interface OrderRepository extends JpaRepository<Order, UUID> {
     public List<Order> findByUserId(UUID userId);

     public List<Order> findByStatusNot(OrderStatus delivered);
}

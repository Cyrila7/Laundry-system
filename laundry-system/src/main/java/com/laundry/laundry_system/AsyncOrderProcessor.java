package com.laundry.laundry_system;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.laundry.laundry_system.model.Order;
import com.laundry.laundry_system.model.OrderStatus;
import com.laundry.laundry_system.repository.OrderRepository;

@Component
public class AsyncOrderProcessor {
    @Autowired
    private OrderRepository orderRepository;

    @Scheduled(fixedRate = 30000) // 30 seconds in milliseconds
    public void processOrders() {

        // fetch orders that are not yet delivered
        List<Order> pendingOrders = orderRepository.findByStatusNot(OrderStatus.DELIVERED);
        for (Order order : pendingOrders) {

            // safety check: skip delivered orders if any
            if (order.getStatus() == OrderStatus.DELIVERED) {
                continue;
            }

            System.out.println("Processing order ID: " + order.getId());
 
            OrderStatus[] statuses = OrderStatus.values();
            OrderStatus nextStatus = statuses[order.getStatus().ordinal() + 1];
            order.setStatus(nextStatus);
            orderRepository.save(order);
            System.out.println("Advanced order ID: " + order.getId() + " to " + nextStatus);
        }
    }
}


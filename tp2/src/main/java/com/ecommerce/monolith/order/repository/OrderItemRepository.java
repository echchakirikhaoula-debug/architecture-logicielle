package com.ecommerce.monolith.order.repository;

import com.ecommerce.monolith.order.model.OrderItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderItemRepository extends JpaRepository<OrderItem, Long> {
}

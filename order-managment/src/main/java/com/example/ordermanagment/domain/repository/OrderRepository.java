package com.example.ordermanagment.domain.repository;

import com.example.ordermanagment.domain.model.Order;
import com.example.ordermanagment.domain.model.OrderId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, OrderId> {
}

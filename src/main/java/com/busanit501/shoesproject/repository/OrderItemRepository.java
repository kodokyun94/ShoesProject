package com.busanit501.shoesproject.repository;


import com.busanit501.shoesproject.domain.OrderItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderItemRepository extends JpaRepository<OrderItem, Long> {

}
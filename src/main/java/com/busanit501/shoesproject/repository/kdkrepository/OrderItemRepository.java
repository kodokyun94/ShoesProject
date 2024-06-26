package com.busanit501.shoesproject.repository.kdkrepository;


import com.busanit501.shoesproject.domain.kdkdomain.OrderItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderItemRepository extends JpaRepository<OrderItem, Long> {

}
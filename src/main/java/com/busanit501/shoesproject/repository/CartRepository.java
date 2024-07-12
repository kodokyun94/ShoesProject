package com.busanit501.shoesproject.repository;


import com.busanit501.shoesproject.domain.Cart;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CartRepository extends JpaRepository<Cart,Long> {
    // 합치기 수정
    Cart findByMemberMid(String memberMid);
}

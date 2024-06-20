package com.busanit501.shoesproject.service.kdkservice;

import com.busanit501.shoesproject.dto.kdkdto.CartDTO;

import java.util.List;

public interface CartService {
    CartDTO saveCart(CartDTO cartDTO);
    CartDTO getCartById(Long id);
    List<CartDTO> getAllCarts();
    void deleteCart(Long id);
}

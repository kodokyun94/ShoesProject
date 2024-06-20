package com.busanit501.shoesproject.service.kdkservice;

import com.busanit501.shoesproject.domain.kdkdomain.CartItem;

import java.util.List;

public interface CartItemService {

    List<CartItem> getAllCartItems();

    Long saveCartItem(CartItem cartItem);

    void deleteCartItem(Long id);
}

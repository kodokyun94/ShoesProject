package com.busanit501.shoesproject.service.kdkservice;

import com.busanit501.shoesproject.domain.kdkdomain.Cart;
import com.busanit501.shoesproject.dto.kdkdto.CartItemDTO;
import com.busanit501.shoesproject.dto.kdkdto.ItemDTO;
import com.busanit501.shoesproject.repository.kdkrepository.CartItemRepository;
import com.busanit501.shoesproject.repository.kdkrepository.CartRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CartService {

    @Autowired
    private CartRepository cartRepository;

    @Autowired
    private CartItemRepository cartItemRepository;

    public Cart getCartById(Long cartId) {
        return cartRepository.findById(cartId).orElse(new Cart());
    }

    public List<CartItemDTO> getCartItems(Long cartId) {
        Cart cart = getCartById(cartId);
        return cart.getCartItems().stream()
                .map(cartItem -> CartItemDTO.builder()
                        .cartId(cartItem.getId())
                        .item(ItemDTO.builder()
                                .itemId(cartItem.getItem().getItemId())
                                .itemName(cartItem.getItem().getItemName())
                                .itemPrice(cartItem.getItem().getItemPrice())
                                .itemType(cartItem.getItem().getItemType())
                                .itemBrand(cartItem.getItem().getItemBrand())
                                .itemGender(cartItem.getItem().getItemGender())
                                .image(cartItem.getItem().getImage())
                                .build())
                        .quantity(cartItem.getQuantity())
                        .build())
                .collect(Collectors.toList());
    }

    public void deleteCartItem(Long id) {
        cartItemRepository.deleteById(id);
    }

}
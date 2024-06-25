package com.busanit501.shoesproject.dto.kdkdto;

import com.busanit501.shoesproject.domain.kdkdomain.CartItem;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class CartDetailDTO {

    private Long cartItemId; //장바구니 상품 아이디

    private String itemName; //상품명

    private String itemPrice; //상품 금액

    private int count; //수량

    private String price;


    public CartDetailDTO(CartItem cartItem) {
        this.cartItemId = cartItem.getCartItemId();
        this.itemName = cartItem.getItem().getItemName(); // itemName은 Item 엔티티에 따라 다를 수 있습니다
        this.count = cartItem.getCount();
        this.price = cartItem.getItem().getItemPrice(); // price는 Item 엔티티에 따라 다를 수 있습니다
    }

}
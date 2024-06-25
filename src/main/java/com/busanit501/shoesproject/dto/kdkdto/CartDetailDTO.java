package com.busanit501.shoesproject.dto.kdkdto;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class CartDetailDTO {

    private Long cartItemId; //장바구니 상품 아이디

    private String itemName; //상품명

    private String itemPrice; //상품 금액

    private int count; //수량


    public CartDetailDTO(Long cartItemId, String itemName, String itemPrice, int count){
        this.cartItemId = cartItemId;
        this.itemName = itemName;
        this.itemPrice = itemPrice;
        this.count = count;
    }

}
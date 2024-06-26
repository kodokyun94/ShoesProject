//package com.busanit501.shoesproject.dto.kdkdto;
//
//
//import com.busanit501.shoesproject.domain.kdkdomain.OrderItem;
//import lombok.Getter;
//import lombok.Setter;
//
//@Getter @Setter
//public class OrderItemDto {
//
//    public OrderItemDto(OrderItem orderItem, String imgUrl){
//        this.itemNm = orderItem.getItem().getItemName();
//        this.count = orderItem.getCount();
//        this.orderPrice = Integer.parseInt(orderItem.getOrderPrice());
//        this.imgUrl = imgUrl;
//    }
//
//    private String itemNm; //상품명
//    private int count; //주문 수량
//
//    private int orderPrice; //주문 금액
//    private String imgUrl; //상품 이미지 경로
//
//}
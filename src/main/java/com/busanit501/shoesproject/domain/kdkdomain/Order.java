//package com.busanit501.shoesproject.domain.kdkdomain;
//
//import jakarta.persistence.*;
//import lombok.Getter;
//import lombok.Setter;
//
//import java.time.LocalDateTime;
//import java.util.ArrayList;
//import java.util.List;
//import java.util.Optional;
//
//@Entity
//@Table(name = "orders")
//@Getter @Setter
//public class Order extends BaseEntity {
//
//    @Id @GeneratedValue
//    @Column(name = "order_id")
//    private Long orderId;
//
//    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "member_id")
//    private Member member;
//
//    private LocalDateTime orderDate; //주문일
//
//    @Enumerated(EnumType.STRING)
//    private OrderStatus orderStatus; //주문상태
//
//    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL
//            , orphanRemoval = true, fetch = FetchType.LAZY)
//    private List<OrderItem> orderItems = new ArrayList<>();
//
//    public void addOrderItem(OrderItem orderItem) {
//        orderItems.add(orderItem);
//        orderItem.setOrder(this);
//    }
//
//    public static Order createOrder(Optional<Member> member, List<OrderItem> orderItemList) {
//        Order order = new Order();
//        member.ifPresent(order::setMember); // Optional에서 Member 객체를 가져와서 설정
//
//        for (OrderItem orderItem : orderItemList) {
//            order.addOrderItem(orderItem);
//        }
//
//        order.setOrderStatus(OrderStatus.ORDER);
//        order.setOrderDate(LocalDateTime.now());
//        return order;
//    }
//
//
//    public int getTotalPrice() {
//        int totalPrice = 0;
//        for(OrderItem orderItem : orderItems){
//            totalPrice += orderItem.getTotalPrice();
//        }
//        return totalPrice;
//    }
//
//    public void cancelOrder() {
//        this.orderStatus = OrderStatus.CANCEL;
//        for (OrderItem orderItem : orderItems) {
//            orderItem.cancel();
//        }
//    }
//
//}
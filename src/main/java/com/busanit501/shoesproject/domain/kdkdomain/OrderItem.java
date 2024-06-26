package com.busanit501.shoesproject.domain.kdkdomain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter @Setter
public class OrderItem extends BaseEntity {

    @Id @GeneratedValue
    @Column(name = "order_item_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "item_id")
    private Item item;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "order_id")
    private Order order;

    private int orderPrice; //주문가격

    private int count; //수량

    public static OrderItem createOrderItem(Item item, int count){
        OrderItem orderItem = new OrderItem();
        orderItem.setItem(item);
        orderItem.setCount(count);
        orderItem.setOrderPrice(item.getItemPrice());

        return orderItem;
    }

    public int getTotalPrice(){
        return orderPrice*count;
    }


    public void cancel() {
        // 예시로 getItem() 메서드가 반환하는 객체에서 addStock() 메서드를 호출하는 예시 코드
        Item item = this.getItem(); // getItem()은 어떻게 구현되어 있는지에 따라 실제 코드를 적절히 변경해야 합니다.
        int count = getCount(); // count 변수는 어디선가 가져오는 값이라고 가정

        if (item != null) {
            item.addStock(count); // getItem()으로 가져온 객체에 count를 넘겨서 재고를 추가하는 예시 코드
        }
    }
}
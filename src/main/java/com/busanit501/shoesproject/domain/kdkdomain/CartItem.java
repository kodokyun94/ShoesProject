package com.busanit501.shoesproject.domain.kdkdomain;

import com.busanit501.shoesproject.domain.nhjdomain.Size;
import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import lombok.*;

@Entity
@Getter @Setter
@Table(name="cartItem")
public class CartItem extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cartItemId")
    private Long cartItemId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="cartId")
    private Cart cart;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "itemId")
    private Item item;

    @OneToOne
    @JoinColumn(name = "sizeId")
    private Size size;

    @Min(value = 1, message = "최소 1개 이상 담아주세요")
    private int count;

    public static CartItem createCartItem(Cart cart, Item item, Size size , int count) {
        CartItem cartItem = new CartItem();
        cartItem.setCart(cart);
        cartItem.setItem(item);
        cartItem.setSize(size);
        cartItem.setCount(count);
        return cartItem;
    }

    public void addCount(int count){
        this.count += count;
    }

    public void updateCount(int count){
        this.count = count;
    }

}

package com.busanit501.shoesproject.domain.kdkdomain;

import com.busanit501.shoesproject.domain.lsjdomain.ShoesMember;
import jakarta.persistence.*;
import lombok.*;

import java.util.Optional;

@Entity
@Table(name = "cart")
@Getter @Setter
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Cart extends BaseEntity {

    @Id
    @Column(name = "cartId")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long cartId;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "shoesMemberId")
    private ShoesMember shoesMember;

//    @OneToMany(mappedBy = "cartItemId", cascade = CascadeType.ALL, orphanRemoval = true)
//    private List<CartItem> cartItems = new ArrayList<>();

    public static Cart createCart(ShoesMember shoesMember) {
        Cart cart = new Cart();
        cart.setShoesMember(shoesMember);
        return cart;
    }
}


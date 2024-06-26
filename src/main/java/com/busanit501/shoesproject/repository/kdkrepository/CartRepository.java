package com.busanit501.shoesproject.repository.kdkrepository;

import com.busanit501.shoesproject.domain.kdkdomain.Cart;
import com.busanit501.shoesproject.domain.kdkdomain.Member;
import com.busanit501.shoesproject.dto.kdkdto.CartDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CartRepository extends JpaRepository<Cart,Long> {
//    Member member = new Member();

    Cart findByMember_MemberId(Long memberId);

    default Cart dtoToEntity(CartDTO cartDTO) {

        Cart cart = Cart.builder()
                .member(cartDTO.getMember())
                .cartId(cartDTO.getCartId())
//                .cartItems(cartDTO.getCartItems())
                .build();
        return cart;
    }

    default CartDTO entityToDTO(Cart cart) {

        CartDTO cartDTO = CartDTO.builder()
                .member(cart.getMember())
                .cartId(cart.getCartId())
//                .cartItems(cart.getCartItems())
                .build();
        return cartDTO;
    }
}

package com.busanit501.shoesproject.repository.kdkrepository;

import com.busanit501.shoesproject.domain.kdkdomain.Cart;
import com.busanit501.shoesproject.domain.lsjdomain.ShoesMember;
import com.busanit501.shoesproject.dto.kdkdto.CartDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CartRepository extends JpaRepository<Cart,Long> {
//    Member member = new Member();

    Cart findByShoesMember(String shoesMember);


    default Cart dtoToEntity(CartDTO cartDTO) {

        Cart cart = Cart.builder()
                .shoesMember(cartDTO.getShoesMember())
                .cartId(cartDTO.getCartId())
//                .cartItems(cartDTO.getCartItems())
                .build();
        return cart;
    }

    default CartDTO entityToDTO(Cart cart) {

        CartDTO cartDTO = CartDTO.builder()
                .shoesMember(cart.getShoesMember())
                .cartId(cart.getCartId())
//                .cartItems(cart.getCartItems())
                .build();
        return cartDTO;
    }
}

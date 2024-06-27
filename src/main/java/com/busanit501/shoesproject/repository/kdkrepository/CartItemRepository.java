package com.busanit501.shoesproject.repository.kdkrepository;

import com.busanit501.shoesproject.domain.kdkdomain.CartItem;
import com.busanit501.shoesproject.dto.kdkdto.CartDetailDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CartItemRepository extends JpaRepository<CartItem, Long> {

    CartItem findByCart_CartIdAndItem_ItemId(Long cartId, Long itemId);

    @Query("SELECT new com.busanit501.shoesproject.dto.kdkdto.CartDetailDTO(ci) FROM CartItem ci WHERE ci.cart.cartId = :cartId")
    List<CartDetailDTO> findCartDetailDtoList(@Param("cartId") Long cartId);

}

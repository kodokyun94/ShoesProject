package com.busanit501.shoesproject.repository.kdkrepository;

import com.busanit501.shoesproject.domain.kdkdomain.CartItem;
import com.busanit501.shoesproject.dto.kdkdto.CartDetailDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CartItemRepository extends JpaRepository<CartItem, Long> {

    CartItem findByCartIdAndItemId(Long cartId, Long itemId);

    @Query("select new com.shop.dto.CartDetailDto(ci.id, i.itemName, i.itemPrice, ci.count) " +
            "from CartItem  ci " +
            "join ci.item i " +
            "where ci.cart.id = :cartId " +
            "and im.item.id = ci.item.id " +
            "order by ci.regTime desc"
    )
    List<CartDetailDto> findCartDetailDtoList(Long cartId);

}

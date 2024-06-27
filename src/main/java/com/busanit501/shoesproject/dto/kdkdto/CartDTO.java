package com.busanit501.shoesproject.dto.kdkdto;

import com.busanit501.shoesproject.domain.kdkdomain.CartItem;
import com.busanit501.shoesproject.domain.lsjdomain.ShoesMember;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;


@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CartDTO {
    private Long cartId;
    private ShoesMember shoesMember;
    private List<CartItem> cartItems;
}

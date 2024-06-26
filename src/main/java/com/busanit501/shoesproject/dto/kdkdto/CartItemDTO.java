package com.busanit501.shoesproject.dto.kdkdto;

import com.busanit501.shoesproject.domain.kdkdomain.Cart;
import com.busanit501.shoesproject.domain.kdkdomain.Item;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CartItemDTO {
    private Long cartItemId;
    private Cart cart;
    private Item item;
    private int count;

}

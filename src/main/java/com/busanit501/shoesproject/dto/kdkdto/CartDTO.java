package com.busanit501.shoesproject.dto.kdkdto;

import com.busanit501.shoesproject.domain.kdkdomain.Item;
import lombok.*;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CartDTO {
    @NonNull
    private Long cart_Id;

//    private Member member;

    private List<ItemDTO> items;

    private Long item_id;

    private Item item;

}

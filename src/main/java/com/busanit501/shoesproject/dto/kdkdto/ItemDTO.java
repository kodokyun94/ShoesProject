package com.busanit501.shoesproject.dto.kdkdto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ItemDTO {
    private Long itemId;
    private String itemName;
    private int itemPrice;
    private String itemType;
    private String itemBrand;
    private String itemGender;
    private String image;
}

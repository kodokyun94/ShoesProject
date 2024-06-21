package com.busanit501.shoesproject.dto.kdkdto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ItemDTO {
    private Long item_id;

    private String item_name;
    private String item_price;
    private String item_type;
    private String item_brand;
    private String item_gender;
}

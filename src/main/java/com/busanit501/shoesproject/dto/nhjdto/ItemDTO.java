package com.busanit501.shoesproject.dto.nhjdto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ItemDTO {
    private Long item_id;
    private String item_name;
    private String item_type;
    private String item_brand;
    private String item_price;
    private String item_review_rank_avg;
    private String item_gender;
}

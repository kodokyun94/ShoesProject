package com.busanit501.shoesproject.dto.nhjdto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ReviewDTO {

    private long item_id;
    private long member_id;
    private String member_name;
    private String item_rev;
    private String item_rank;

}

package com.busanit501.shoesproject.dto.mjsdto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class ShoesListReplyCountDTO {
    private Long item_id;
    private String item_name;
    private String item_type;
    private String item_brand;
    private String item_price;
    private String item_review_rank_avg;
    private String item_gender;

    //댓글 갯수 표기 하기 위한 용도.
    private Long replyCount;
}

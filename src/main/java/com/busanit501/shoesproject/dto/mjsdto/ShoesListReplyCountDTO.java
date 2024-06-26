package com.busanit501.shoesproject.dto.mjsdto;

import lombok.Data;

@Data
public class ShoesListReplyCountDTO {
    private Long itemId;

    private String itemName;

    private String itemType;

    private String itemBrand;

    private int itemPrice;

    private String itemReviewRankAvg;

    private String itemGender;


    //댓글 갯수 표기 하기 위한 용도.
    private Long replyCount;
}

package com.busanit501.shoesproject.dto.mjsdto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ShoesListAllDTO {
    private Long itemId;

    private String itemName;

    private String itemType;

    private String itemBrand;

    private String itemPrice;

    private String itemReviewRankAvg;

    private String itemGender;


    //댓글 갯수 표기 하기 위한 용도.
    private Long replyCount;
    // 첨부 이미지들
    private List<ShoesImageDTO> shoesImages;
}

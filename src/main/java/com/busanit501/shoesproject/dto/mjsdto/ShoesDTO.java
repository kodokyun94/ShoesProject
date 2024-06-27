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
public class ShoesDTO {
    private Long itemId;

    private String itemName;

    private String itemType;

    private String itemBrand;

    private int itemPrice;

    private String itemReviewRankAvg;

    private String itemGender;

    private List<String> fileNames;



}

package com.busanit501.shoesproject.dto.kdkdto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class ItemDto {

    private Long itemId;
    private String itemName;
    private int itemPrice;
    private String itemDetail;
    private String sellStatCd;

    private LocalDateTime regTime;

    private LocalDateTime updateTime;
    private String itemType;
    private String itemBrand;
    private String itemGender;
    private String image;

}
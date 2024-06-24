package com.busanit501.shoesproject.domain.kdkdomain;

import jakarta.persistence.*;
import lombok.*;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity
public class Item {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long itemId;

    private String itemName;
    private String itemPrice;
    private String itemType;
    private String itemBrand;
    private String itemGender;
    private String image; // 상품 이미지 경로 필드 추가

    // CartItem과의 관계 설정은 생략
}
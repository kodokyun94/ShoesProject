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
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long itemId;

    @Column(length=50, nullable = false)
    private String itemName;

    @Column(length=50, nullable = false)
    private String itemType;

    @Column(length=50, nullable = false)
    private String itemBrand;

    @Column(length=50, nullable = false)
    private String itemPrice;

    //@Column(length=50, nullable = false)
    //private String review_rank_avg;

    @Column(length=50, nullable = false)
    private String itemGender;
}
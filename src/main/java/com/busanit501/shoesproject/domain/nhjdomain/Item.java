package com.busanit501.shoesproject.domain.nhjdomain;

import jakarta.persistence.*;
import lombok.*;

@Builder
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity
public class Item {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long item_id;

    @Column(length=50, nullable = false)
    private String item_name;

    @Column(length=50, nullable = false)
    private String item_type;

    @Column(length=50, nullable = false)
    private String item_brand;

    @Column(length=50, nullable = false)
    private String item_price;

    @Column(length=50, nullable = false)
    private String item_review_rank_avg;

    @Column(length=50, nullable = false)
    private String item_gender;

}

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
    private Long itemId;

    @Column(length=50, nullable = false)
    private String name;

    @Column(length=50, nullable = false)
    private String type;

    @Column(length=50, nullable = false)
    private String brand;

    @Column(length=50, nullable = false)
    private String price;

    //@Column(length=50, nullable = false)
    //private String review_rank_avg;

    @Column(length=50, nullable = false)
    private String gender;

}
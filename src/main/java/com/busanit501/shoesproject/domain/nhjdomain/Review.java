package com.busanit501.shoesproject.domain.nhjdomain;

import jakarta.persistence.*;
import lombok.*;

@Builder
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
//@Table(name="reviews")
@Entity
public class Review {

    @Id
//    pr키
//    나중에 합치고 아래변경
//    @OneToMany
//    @JoinColumn(name = "item_id")


    private long item_id;

//    @OneToMany
//    @JoinColumn(name = "member_id")
    private long member_id;

    @Column(length=50, nullable = false)
    private String member_name;

    @Column(length=50, nullable = false)
    private String item_rev;

    @Column(length=50, nullable = false)
    private String item_rank;


}

package com.busanit501.shoesproject.domain.nhjdomain;

import jakarta.persistence.*;
import lombok.*;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity
public class Review extends BaseEntity {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long rev_id;

    //    나중에 합치고 아래변경
    @ManyToOne
    @JoinColumn(name = "item_id")
    private Item item;

    @ManyToOne
    @JoinColumn(name = "member_id")
    private Member member;

    @Column(length=50, nullable = false)
    private String member_name;

    private String imagePath;

    @Column(length=200, nullable = false)
    private String item_rev;

    @Column(length=5, nullable = false)
    private int item_rank;

}

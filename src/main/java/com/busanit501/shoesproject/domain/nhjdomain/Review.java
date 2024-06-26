package com.busanit501.shoesproject.domain.nhjdomain;

import com.busanit501.shoesproject.domain.kdkdomain.Item;
import com.busanit501.shoesproject.domain.lsjdomain.ShoesMember;
import com.busanit501.shoesproject.domain.mjsdomain.Shoes;
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
    private Long revId;

    //    나중에 합치고 아래변경
    @ManyToOne
    @JoinColumn(name = "itemId")
    private Shoes shoes;

    @ManyToOne
    @JoinColumn(name = "memberId")
    private ShoesMember shoesmember;

    //@Column(length=50, nullable = false)
    //private String member_name;

    private String imagePath;

    @Column(length=200, nullable = false)
    private String content;

    @Column(length=5, nullable = false)
    private int rating;

}

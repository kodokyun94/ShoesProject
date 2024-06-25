package com.busanit501.shoesproject.domain.mjsdomain;

import jakarta.persistence.*;
import lombok.*;

@Builder
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity
@Table(name = "member2", indexes = {
        @Index(name = "idx_member_shoes_itemId", columnList = "shoes_itemId")
})
public class Member2 {

    @Id
    @Column(length=50, nullable = false)
    private String memberId;

    @Column(length=50, nullable = false)
    private String memberPw;

    @Column(length=50, nullable = false)
    private String memberName;

    @Column(length=50, nullable = false)
    private String memberPhone;

    @Column(length=50, nullable = false)
    private String memberEmail;

    @Column(length=50, nullable = false)
    private String memberAddress;

    @ManyToOne(fetch = FetchType.LAZY)
    private Shoes shoes;

}
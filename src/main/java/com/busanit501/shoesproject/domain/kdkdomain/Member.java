package com.busanit501.shoesproject.domain.kdkdomain;

import jakarta.persistence.*;
import lombok.*;

@Builder
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
// 이클래스는 실제 데이터베이스에 영향이 있다.asd
// 반드시, pk 있어야 함.
@Entity
public class Member {
    @Id
    @Column(length=50, nullable = false)
    private Long memberId;

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
}


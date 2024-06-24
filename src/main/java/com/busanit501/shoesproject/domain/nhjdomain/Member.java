package com.busanit501.shoesproject.domain.nhjdomain;

import jakarta.persistence.*;
import lombok.*;

@Builder
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity
@Table (name = "shoes_member")
public class Member {

    @Id
    @Column(length=50, nullable = false)
    private String memberId;

    @Column(length=50, nullable = false)
    private String pw;

    @Column(length=50, nullable = false)
    private String name;

    @Column(length=50, nullable = false)
    private String phone;

    @Column(length=50, nullable = false)
    private String email;

    @Column(length=50, nullable = false)
    private String address;

}
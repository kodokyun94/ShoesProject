package com.busanit501.shoesproject.domain.nhjdomain;

import jakarta.persistence.*;
import lombok.*;

@Builder
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity
public class Member {

    @Id
    @Column(length=50, nullable = false)
    private String member_id;

    @Column(length=50, nullable = false)
    private String member_pw;

    @Column(length=50, nullable = false)
    private String member_name;

    @Column(length=50, nullable = false)
    private String member_phone;

    @Column(length=50, nullable = false)
    private String member_email;

    @Column(length=50, nullable = false)
    private String member_address;

}

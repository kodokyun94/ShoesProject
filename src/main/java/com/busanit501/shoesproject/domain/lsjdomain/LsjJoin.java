package com.busanit501.shoesproject.domain.lsjdomain;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.*;

@Builder
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity

public class LsjJoin extends LsjJoinEntity{


    @Id
    private Long member_id;

    @Column(length = 50, nullable = false)
    private String member_pw;

    @Column(length = 50, nullable = false)
    private String member_name ;

    @Column(length = 50, nullable = false)
    private String member_phone;

    @Column(length = 50, nullable = false)
    private String member_email;

    @Column(length = 50, nullable = false)
    private String member_address;

    public void change(String member_pw, String member_name, String member_phone, String member_email, String member_address) {
        this.member_pw = member_pw;
        this.member_name = member_name;
        this.member_phone = member_phone;
        this.member_email = member_email;
        this.member_address = member_address;
    }


}

package com.busanit501.shoesproject.domain.lsjdomain;

import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import lombok.*;

import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString(exclude = "roleSet")
public class Shoes extends  BaseEntity {
    @Id
    private String member_id;
    private String member_pw;
    private String member_name;
    private String member_phone;
    private String member_email;
    private String member_address;

//    //이미지 파일명 필요해서,
//    // 프로필 이미지 조회시 사용.
//    private String uuid;
//    private String fileName;

    // 멤버를 조회시 roleSet 를 같이 조회를 하기.
    @ElementCollection(fetch = FetchType.LAZY)
    @Builder.Default
    private Set<ShoesRole> roleSet = new HashSet<>();

    // 세터 대신에, 임의의 멤버의 필드를 교체하는 메서드 만들기 => set 랑 비슷함.
    public void changePassword(String member_pw) {
        this.member_pw = member_pw;
    }
    public void changeName(String member_name) {
        this.member_name = member_name;
    }
    public void changePhone(String member_phone) {
        this.member_phone = member_phone;
    }
    public void changeEmail(String member_email) {
        this.member_email = member_email;
    }
    public void changeAddress(String member_address) {
        this.member_address = member_address;
    }
    public void addRole(ShoesRole shoesRole) {
        this.roleSet.add(shoesRole);

    }
    public void clearRole() {
        this.roleSet.clear();
    }


//    public void changeUuidFileName(String uuid, String fileName) {
//        this.uuid = uuid;
//        this.fileName = fileName;
//    }

}

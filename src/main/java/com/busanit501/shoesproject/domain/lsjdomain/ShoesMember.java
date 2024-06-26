package com.busanit501.shoesproject.domain.lsjdomain;

import jakarta.persistence.*;
import lombok.*;

import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString(exclude = "roleSet")
public class ShoesMember extends  BaseEntity {
    @Id
    private String memberId;
    private String memberPw;
    private String memberName ;
    private String memberPhone;
    private String memberEmail;
    private boolean memberSocial;
    private boolean memberDel;

    //이미지 파일명 필요해서,
    // 프로필 이미지 조회시 사용.
//    private String uuid;
//    private String fileName;

    // 멤버를 조회시 roleSet 를 같이 조회를 하기.
    @ElementCollection(fetch = FetchType.LAZY)
    @Builder.Default
    private Set<ShoesRole> roleSet = new HashSet<>();

    // 세터 대신에, 임의의 멤버의 필드를 교체하는 메서드 만들기 => set 랑 비슷함.
    public void changePassword(String memberPw) {
        this.memberPw = memberPw;
    }
    public void changeName(String memberName) {
        this.memberName = memberName;
    }
    public void changePhone(String memberPhone) {
        this.memberPhone = memberPhone;
    }
    public void changeEmail(String memberEmail) {
        this.memberEmail = memberEmail;
    }
    public void changeDel(boolean memberDel) {
        this.memberDel = memberDel;
    }

    public void addRole(ShoesRole memberRole) {
        this.roleSet.add(memberRole);

    }
    public void clearRole() {
        this.roleSet.clear();
    }

    public void changeSocial(boolean memberSocial) {
        this.memberSocial = memberSocial;
    }


}

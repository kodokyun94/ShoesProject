package com.busanit501.shoesproject.security.dto;

//시큐리티 필터 설정이 되어 있고,
// 로그인 처리를 우리가 하는게 아니라, 시큐리티가 함.
// 시큐리티는 그냥 클래스를 요구하지 않고,
// 자기들이 정해둔 룰. UserDetails 를 반환하는 클래스를 요구를 해요.
// 시큐리티에서 정의해둔 특정 클래스를 상속을 받으면 됨.

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.apache.catalina.Group;
import org.apache.catalina.Role;
import org.apache.catalina.UserDatabase;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.oauth2.core.user.OAuth2User;


import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

@Getter
@Setter
@ToString
//@AllArgsConstructor
// @AllArgsConstructor 대신에 권한도, 시큐리티에서 가져와서, 사용자정의해야하서.
public class ShoesSecurityDTO extends User implements OAuth2User {
//public class ShoesSecurityDTO extends User implements OAuth2User 이건데
// 오류 떠서 이렇게 바뀜.
    private String memberId;
    private String memberPw;
    private String memberName;
    private String memberPhone;
    private String memberEmail;
    private boolean memberSocial;
    private boolean memberDel;

    //소셜 로그인 정보
    private Map<String, Object> props;

    //생성자
    public ShoesSecurityDTO(
            //로그인한 유저이름.
            String username, String password, String memberEmail,
            String memberName, String memberPhone,
            boolean memberSocial,boolean memberDel,
            //GrantedAuthority 를 상속한 클래스는 아무나 올수 있다. 타입으로
            Collection<? extends GrantedAuthority> authorities
    ){
        super(username, password, authorities);
      this.memberId = username;
      this.memberPw = password;
      this.memberName = memberName;
      this.memberPhone = memberPhone;
      this.memberEmail = memberEmail;
      this.memberSocial = memberSocial;
      this.memberDel = memberDel;
    }


    // 카카오 인증 연동시 , 필수 재정의 메서드
    @Override
    public Map<String, Object> getAttributes() {
        return this.getProps();
    }

    @Override
    public String getName() {
        return this.memberId;
    }





}

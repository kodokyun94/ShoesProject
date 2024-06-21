package com.busanit501.shoesproject.security.dto;

//시큐리티 필터 설정이 되어 있고,
// 로그인 처리를 우리가 하는게 아니라, 시큐리티가 함.
// 시큐리티는 그냥 클래스를 요구하지 않고,
// 자기들이 정해둔 룰. UserDetails 를 반환하는 클래스를 요구를 해요.
// 시큐리티에서 정의해둔 특정 클래스를 상속을 받으면 됨.

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.util.Collection;
import java.util.List;

@Getter
@Setter
@ToString
//@AllArgsConstructor
// @AllArgsConstructor 대신에 권한도, 시큐리티에서 가져와서, 사용자정의해야하서.
public class MemberSecurityDTO extends User {
    private String member_id;
    private String member_pw;
    private String member_name;
    private String member_phone;
    private String member_email;
    private String member_address;

    //생성자
    public MemberSecurityDTO(
            //로그인한 유저이름.
            String id, String pw, String email, String name,
            String phone, String address,
            //GrantedAuthority 를 상속한 클래스는 아무나 올수 있다. 타입으로
            Collection<? extends GrantedAuthority> authorities
    ){
      super(String.valueOf(id), pw, authorities);
      this.member_id = String.valueOf(id);
      this.member_pw = pw;
      this.member_name = name;
      this.member_phone = phone;
      this.member_email = email;
      this.member_address = address;
    }

}

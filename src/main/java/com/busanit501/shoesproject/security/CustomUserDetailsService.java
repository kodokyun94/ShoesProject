package com.busanit501.shoesproject.security;

import com.busanit501.shoesproject.domain.lsjdomain.Shoes;
import com.busanit501.shoesproject.repository.lsjrepository.ShoesRepository;
import com.busanit501.shoesproject.security.dto.ShoesSecurityDTO;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.stream.Collectors;

@Log4j2
@Service

public class CustomUserDetailsService implements UserDetailsService {

    // 스프링 시큐리티 설정 클래스에 빈으로 등록한 인스턴스 주입하기.
    private PasswordEncoder passwordEncoder;

    // MemberRepository 넣기.
    @Autowired
    private ShoesRepository shoesRepository;

    //생성자로 주입하기.
    public CustomUserDetailsService() {
        this.passwordEncoder = new BCryptPasswordEncoder();
    }

    // 로그인 로직 처리시, 여기를 반드시 거쳐 감.
    // username : 로그인한 유저이름.
    @Override
    public UserDetails loadUserByUsername(String id) throws UsernameNotFoundException {
        log.info("CustomUserDetailsService loadUserByUsername 확인 : "+ id);
        Optional<Shoes> result = shoesRepository.getWithRoles(id);

        if(result.isEmpty()){
            //예외 처리.
            throw new UsernameNotFoundException("유저가 존재하지 않습니다");
        }
        // 디비에 해당 유저가 있다면, 이어서 로그인 처리하기.
        Shoes shoes = result.get();

        ShoesSecurityDTO shoesSecurityDTO = new ShoesSecurityDTO(
                shoes.getMember_id(),
                shoes.getMember_pw(),
                shoes.getMember_name(),
                shoes.getMember_email(),
                shoes.getMember_phone(),
                shoes.getMember_address(),
                shoes.getRoleSet().stream().map(
                        memberRole -> new SimpleGrantedAuthority("ROLE_"+ memberRole.name())
                ).collect(Collectors.toList())
        );
        log.info("CustomUserDetailsService loadUserByUsername memberSecurityDTO 확인 :" + shoesSecurityDTO);

        return shoesSecurityDTO;
    }
}
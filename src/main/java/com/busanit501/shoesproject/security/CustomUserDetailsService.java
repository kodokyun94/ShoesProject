package com.busanit501.shoesproject.security;

import com.busanit501.shoesproject.domain.lsjdomain.ShoesMember;
import com.busanit501.shoesproject.repository.lsjrepository.lsjShoesRepository;
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
    private lsjShoesRepository lsjShoesRepository;

    //생성자로 주입하기.
    public CustomUserDetailsService() {
        this.passwordEncoder = new BCryptPasswordEncoder();
    }

    // 로그인 로직 처리시, 여기를 반드시 거쳐 감.
    // username : 로그인한 유저이름.
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        log.info("CustomUserDetailsService loadUserByUsername 확인 : "+ username);
        Optional<ShoesMember> result = lsjShoesRepository.getWithRoles(username);

        if(result.isEmpty()){
            log.info("CustomUserDetailsService loadUserByUsername 확인2 result.isEmpty() : ");
            //예외 처리.
            throw new UsernameNotFoundException("유저가 존재하지 않습니다");

        }
        // 디비에 해당 유저가 있다면, 이어서 로그인 처리하기.
        ShoesMember shoesMember = result.get();
        log.info("CustomUserDetailsService loadUserByUsername 확인3 shoesMember : " + shoesMember);

        ShoesSecurityDTO shoesSecurityDTO = new ShoesSecurityDTO(
                shoesMember.getMemberId(),
                shoesMember.getMemberPw(),
                shoesMember.getMemberName(),
                shoesMember.getMemberEmail(),
                shoesMember.getMemberPhone(),
                false,
                shoesMember.isMemberDel(),
                shoesMember.getRoleSet().stream().map(
                        shoesRole -> new SimpleGrantedAuthority("ROLE_"+shoesRole.name())
                ).collect(Collectors.toList())
        );

        log.info("CustomUserDetailsService loadUserByUsername memberSecurityDTO 확인4 :" + shoesSecurityDTO);

        return  shoesSecurityDTO;
    }
}
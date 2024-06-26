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

    private PasswordEncoder passwordEncoder;

    @Autowired
    private lsjShoesRepository lsjShoesRepository;

    public CustomUserDetailsService() {
        this.passwordEncoder = new BCryptPasswordEncoder();
    }

    // 로그인 로직 처리시, 여기를 반드시 거쳐 감.
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        log.info("CustomUserDetailsService loadUserByUsername 확인 : "+ username);

        // 로그인 한 유저명으로, 디비에서 검색을함.
        Optional<ShoesMember> result = lsjShoesRepository.getWithRoles(username);

        if(result.isEmpty()){
            throw new UsernameNotFoundException("유저가 존재하지 않습니다");
        }

        ShoesMember shoesMember = result.get();

        ShoesSecurityDTO shoesSecurityDTO = new ShoesSecurityDTO(
          shoesMember.getMemberId(),
          shoesMember.getMemberPw(),
          shoesMember.getMemberName(),
          shoesMember.getMemberEmail(),
          shoesMember.getMemberPhone(),
        false,
            shoesMember.isMemberDel(),
                shoesMember.getRoleSet().stream().map(
                    memberRole -> new SimpleGrantedAuthority("ROLE_"+ memberRole.name())
            ).collect(Collectors.toList())
        );
        log.info("CustomUserDetailsService loadUserByUsername shoesSecurityDTO 확인 :" + shoesSecurityDTO);

        return shoesSecurityDTO;
    }
}

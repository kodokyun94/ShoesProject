package com.busanit501.shoesproject.security.handler;

import com.busanit501.shoesproject.security.dto.ShoesSecurityDTO;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import java.io.IOException;

@Log4j2
@RequiredArgsConstructor
public class CustomSocialLoginSuccessHandler implements AuthenticationSuccessHandler {

    private final PasswordEncoder passwordEncoder;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        log.info("=====CustomSocialLoginSuccessHandler  onAuthenticationSuccess 확인 ===============================");
        log.info(authentication.getPrincipal());

        ShoesSecurityDTO shoesSecurityDTO = (ShoesSecurityDTO) authentication.getPrincipal();

        String encodePw = shoesSecurityDTO.getMemberPw();

        // 소셜 로그인은 무조건 패스워드를 1111 , 설정
        // 변경이 필요함.
        if(shoesSecurityDTO.isMemberSocial() && shoesSecurityDTO.getMemberPw().equals("1113") || passwordEncoder.matches("1113", shoesSecurityDTO.getMemberPw())) {
            log.info("패스워드를 변경해주세요.");
            log.info("회원 정보 변경하는 페이지로 리다이렉트, 마이 페이지가 없음. 일단 수동으로 임의로 변경하기 ");
            response.sendRedirect("/member/signin");
            return;
        } else {
            response.sendRedirect("/shoes/main");
        }
    }
}

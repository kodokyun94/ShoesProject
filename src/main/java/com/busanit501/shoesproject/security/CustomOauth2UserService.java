package com.busanit501.shoesproject.security;

import com.busanit501.shoesproject.domain.lsjdomain.ShoesMember;
import com.busanit501.shoesproject.domain.lsjdomain.ShoesRole;
import com.busanit501.shoesproject.repository.lsjrepository.lsjShoesRepository;
import com.busanit501.shoesproject.security.dto.ShoesSecurityDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.client.registration.ClientRegistration;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Log4j2
@RequiredArgsConstructor
public class CustomOauth2UserService extends DefaultOAuth2UserService {
    private final lsjShoesRepository lsjShoesRepository;
    private final PasswordEncoder passwordEncoder;

    // 카카오 소셜 로그인시 , 로그인 로직 처리를 여기서 함.
    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
        // userRequest, 카카오 로그인 관련 정보가 들어가 있다.
        log.info("CustomOauth2UserService : userRequest = " + userRequest);

        ClientRegistration clientRegistration = userRequest.getClientRegistration();
        String clientName = clientRegistration.getClientName();

        log.info("CustomOauth2UserService : clientRegistration : clientName = " + clientName);

        OAuth2User oAuth2User = super.loadUser(userRequest);
        Map<String, Object> paramMap = oAuth2User.getAttributes();

        paramMap.forEach((k, v) -> {
            log.info("CustomOauth2UserService : k = " + k + " v = " + v);
        });

        String email = null;
        String phone = null;
//        String profileUrlThumbnail = null;

        switch (clientName) {
            case "kakao":
                // 소셜 로그인 정보에서, 이메일만 추출.
//                email = getKakaoEmail(paramMap);
                // 이메일 정보가 없어서, 더미로 일단 테스트
                email = "lsy1234@naver.com";
                // 소셜 로그인 정보에서, 프로필 이미지 외부 미디어 서버 주소 추출.
//                profileUrlThumbnail = getKakaoProfile(paramMap);
//                profileUrlThumbnail = null;
                break;
        }

        log.info("CustomOauth2UserService : email = " + email);


        return generateDTO(email,phone, paramMap);
    }

    private ShoesSecurityDTO generateDTO( String email, String phone , Map<String, Object> paramMap) {

        Optional<ShoesMember> result = lsjShoesRepository.findByMemberEmail(email);
        //디비에 유저가 없다면 , 소셜로그인. (이메일포함)
        // 일반 로그인으로 로그인시 (가입한 이메일)
        if (result.isEmpty()) {
            // 회원 추가 하기, mid: 이메일, 패스워드 : 임시로 무조건 1111 , 로하기.
            ShoesMember shoesMember = ShoesMember.builder()

                    .memberId(email)
                    .memberPw(passwordEncoder.encode("1111"))
//                    .memberName(name)
                    .memberEmail(email)
                    .memberPhone(phone)
                    .memberSocial(true)
                    .build();

            //권한, 일반 USER
            shoesMember.addRole(ShoesRole.USER);
            lsjShoesRepository.save(shoesMember);

            ShoesSecurityDTO shoesSecurityDTO = new ShoesSecurityDTO(email,"1111", email,"",
                    phone,true, false, Arrays.asList(
                            new SimpleGrantedAuthority("ROLE_USER")
            ));
            shoesSecurityDTO.setProps(paramMap);
            log.info("소셜 로그인 , 최초 로그인 했을 경우, 성공 후 반환");
            return shoesSecurityDTO;
        }

        else{
            ShoesMember shoesMember = result.get();
            ShoesSecurityDTO shoesSecurityDTO  =
                    new ShoesSecurityDTO(
                            shoesMember.getMemberId(),
                            shoesMember.getMemberPw(),
                            shoesMember.getMemberName(),
                            shoesMember.getMemberEmail(),
                            shoesMember.getMemberPhone(),
                            shoesMember.isMemberSocial(),
                            shoesMember.isMemberDel(),
                            shoesMember.getRoleSet().stream().map(
                                    shoesRole -> new SimpleGrantedAuthority("ROLE_" + shoesRole.name())
                            ).collect(Collectors.toList())
                    );
            log.info("소셜 로그인 , 이미 로그인 했을 경우, 성공 후 반환");
            return shoesSecurityDTO;
        }
    }

    private String getKakaoEmail(Map<String, Object> paramMap) {
        log.info("CustomOauth2UserService : kakao = ");

        Object value = paramMap.get("kakao_account");
        log.info("CustomOauth2UserService : kakao_account = " + value);

        LinkedHashMap accountMap = (LinkedHashMap) value;

        String email = (String) accountMap.get("email");
        log.info("CustomOauth2UserService : email = " + email);
        return email;
    }
// paramMap : 소셜 로그인 정보가 다 들어가 있음.
    private String getKakaoProfile(Map<String, Object> paramMap) {
        log.info("CustomOauth2UserService : kakao = ");

        Object value = paramMap.get("properties");
        log.info("CustomOauth2UserService : properties = " + value);

        LinkedHashMap propertiesMap = (LinkedHashMap) value;

        String thumbnail_image = (String) propertiesMap.get("thumbnail_image");
        log.info("CustomOauth2UserService : thumbnail_image = " + thumbnail_image);
        return thumbnail_image;
    }

}

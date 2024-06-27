package com.busanit501.shoesproject.repository.lsjRepository;


import com.busanit501.shoesproject.domain.lsjdomain.ShoesMember;
import com.busanit501.shoesproject.domain.lsjdomain.ShoesRole;
import com.busanit501.shoesproject.repository.lsjrepository.lsjShoesRepository;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.annotation.Commit;

import java.util.Optional;
import java.util.stream.IntStream;

@SpringBootTest
@Log4j2
public class lsjShoesRepositoryTests {
    @Autowired
    private lsjShoesRepository lsjShoesRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Test
    public void insertMemberTest() {
        // 샘플로 100명의 더미 디비 넣기. 병렬처리
        IntStream.rangeClosed(1,100).forEach(i ->{
            ShoesMember shoesMember = ShoesMember.builder()
                    .memberId("user"+i)
                    // 주의사항, 멤버 넣을 때, 패스워드 평문 안됨, 암호화 필수.
                    .memberPw(passwordEncoder.encode("1234"))
                    .memberName("user")
                    .memberEmail("user"+i+"@gmail.com")
                    .memberPhone("010-1234-5678")
                    .build();

            // 권한주기. USER, ADMIN
            shoesMember.addRole(ShoesRole.USER);
            // 90번 이상부터는, 동시권한, USER 이면서 ADMIN 주기.
            if(i >= 90) {
                shoesMember.addRole(ShoesRole.ADMIN);
            }

            // 엔티티 클래스를 저장, 실제 디비 반영이되는 비지니스 모델.
            lsjShoesRepository.save(shoesMember);

        });
    } // 닫기

    @Test
    public void testRead() {
        Optional<ShoesMember> result = lsjShoesRepository.getWithRoles("ddd2");
        ShoesMember shoesMember = result.orElseThrow();

        log.info("shoesMemberRepositoryTests testRead, shoesMember:  "+ shoesMember);
        log.info("shoesMemberRepositoryTests testRead, shoesMember.getRoleSet():  "+ shoesMember.getRoleSet());

        shoesMember.getRoleSet().forEach(shoesMemberRole -> {
            log.info("MemberRepositoryTests testRead, memberRole:  "+ shoesMemberRole);
        });
    }

    // 소셜 로그인 유저 , 기본 패스워드 1111 고정을 , 변경하는 테스트.
    @Commit
    @Test
    public void testUpdate() {
        String memberId = "lsy1234@naver.com";
        String memberPw = passwordEncoder.encode("0326");
        lsjShoesRepository.updatePassword(memberId, memberPw);
    }


}

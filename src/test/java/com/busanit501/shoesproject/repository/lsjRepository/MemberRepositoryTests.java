package com.busanit501.shoesproject.repository.lsjRepository;


import com.busanit501.shoesproject.domain.lsjdomain.Member;
import com.busanit501.shoesproject.domain.lsjdomain.MemberRole;
import com.busanit501.shoesproject.repository.lsjrepository.MemberRepository;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Optional;
import java.util.stream.IntStream;

@SpringBootTest
@Log4j2
public class MemberRepositoryTests {
    @Autowired
    private MemberRepository memberRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Test
    public void insertMemberTest() {
        // 샘플로 100명의 더미 디비 넣기. 병렬처리
        IntStream.rangeClosed(1,100).forEach(i ->{
            Member member = Member.builder()
                    .member_id(Long.valueOf("user"+i))
                    // 주의사항, 멤버 넣을 때, 패스워드 평문 안됨, 암호화 필수.
                    .member_pw(passwordEncoder.encode("1234"))
                    .member_name("user")
                    .member_email("user"+i+"@gmail.com")
                    .member_phone("010-1234-5678")
                    .member_address("부산 서면 부산it센터")
                    .build();
            // 권한주기. USER, ADMIN
            member.addRole(MemberRole.USER);
            // 90번 이상부터는, 동시권한, USER 이면서 ADMIN 주기.
            if(i >= 90) {
                member.addRole(MemberRole.ADMIN);
            }

            // 엔티티 클래스를 저장, 실제 디비 반영이되는 비지니스 모델.
            memberRepository.save(member);

        });
    } // 닫기

    @Test
    public void testRead() {
        Optional<Member> result = memberRepository.getWithRoles("user");
        Member member = result.orElseThrow();

        log.info("MemberRepositoryTests testRead, member:  "+member);
        log.info("MemberRepositoryTests testRead, member.getRoleSet():  "+member.getRoleSet());

        member.getRoleSet().forEach(memberRole -> {
            log.info("MemberRepositoryTests testRead, memberRole:  "+memberRole);
        });
    }

}

package com.busanit501.shoesproject.ServiceTests.LSJServiceTests;


import com.busanit501.shoesproject.domain.lsjdomain.LsjJoin;
import com.busanit501.shoesproject.dto.lsjdto.LsjJoinDTO;
import com.busanit501.shoesproject.service.lsjservice.JoinService;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.TestPropertySource;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
@Transactional
@TestPropertySource(locations = "classpath:application.properties")
public class JoinServiceTests {
    @Autowired
    JoinService joinService;

    @Autowired
    PasswordEncoder passwordEncoder;

    public LsjJoin createMember(){
        LsjJoinDTO lsjJoinDTO = new LsjJoinDTO();
        lsjJoinDTO.setMember_name("이수진");
        lsjJoinDTO.setMember_email("test@email.com");
        lsjJoinDTO.setMember_phone("010-1234-5678");
        lsjJoinDTO.setMember_address("서면 부산IT 501호");
        lsjJoinDTO.setMember_pw("1234");
        return LsjJoin.createMember(lsjJoinDTO, passwordEncoder);
    }

    @Test
    @DisplayName("회원가입 테스트")
    public void saveMemberTest() {
        LsjJoin lsjJoin = createMember();
        LsjJoin savedMember = joinService.saveMember(lsjJoin);

        assertEquals(lsjJoin.getMember_name(),savedMember.getMember_name());
        assertEquals(lsjJoin.getMember_email(),savedMember.getMember_email());
        assertEquals(lsjJoin.getMember_phone(),savedMember.getMember_phone());
        assertEquals(lsjJoin.getMember_address(),savedMember.getMember_address());
        assertEquals(lsjJoin.getMember_pw(),savedMember.getMember_pw());
        assertEquals(lsjJoin.getRole(),savedMember.getRole());
    }

}

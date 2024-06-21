package com.busanit501.shoesproject.service.lsjservice;

import com.busanit501.shoesproject.domain.lsjdomain.Member;
import com.busanit501.shoesproject.domain.lsjdomain.MemberRole;
import com.busanit501.shoesproject.dto.lsjdto.MemberJoinDTO;
import com.busanit501.shoesproject.repository.lsjrepository.MemberRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@Log4j2
@RequiredArgsConstructor
public class MemberServiceImpl implements MemberService {

    // 다른 기능들 도움 받기, 의존, 주입, 포함 관계 ,
    private final ModelMapper modelMapper;
    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;


    @Override
    public void join(MemberJoinDTO memberJoinDTO) throws MidExistException {
        //기존 아이디와 중복되는지 여부 확인
        String member_id = String.valueOf(memberJoinDTO.getMember_id());
        boolean existMember = memberRepository.existsById(member_id);
        if (existMember) {
            throw new MidExistException();
        }

        // 중복이 아니니 회원 가입 처리하기.
//         Member member = modelMapper.map(memberJoinDTO, Member.class);
        log.info("memberJoinDTO = 4 MemberServiceImpl 프로필 이미지 있는 경우  " + memberJoinDTO);
        Member member = dtoToEntity(memberJoinDTO);
        //패스워드는 현재 평문 -> 암호로 변경.
        member.changePassword(passwordEncoder.encode(member.getMember_pw()));
        // 역할 추가. 기본 USER
        member.addRole(MemberRole.USER);

        // 데이터 가 잘 알맞게 변경이 됐는지 여부,
        log.info("joinMember: " + member);
        log.info("joinMember: " + member.getRoleSet());

        // 디비에 적용하기.
        memberRepository.save(member);

    }
}

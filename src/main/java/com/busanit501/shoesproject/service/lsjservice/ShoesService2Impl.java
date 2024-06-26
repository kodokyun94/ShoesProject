package com.busanit501.shoesproject.service.lsjservice;


import com.busanit501.shoesproject.domain.lsjdomain.ShoesMember;
import com.busanit501.shoesproject.domain.lsjdomain.ShoesRole;
import com.busanit501.shoesproject.dto.lsjdto.ShoesJoinDTO;
import com.busanit501.shoesproject.repository.lsjrepository.lsjShoesRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@Log4j2
@RequiredArgsConstructor
public class ShoesService2Impl implements ShoesService2 {

    // 다른 기능들 도움 받기, 의존, 주입, 포함 관계 ,
    private final ModelMapper modelMapper;
    private final lsjShoesRepository lsjShoesRepository;
    private final PasswordEncoder passwordEncoder;


    @Override
    public void join(ShoesJoinDTO shoesJoinDTO) throws IdExistException {
        //기존 아이디와 중복되는지 여부 확인
        String memberId = shoesJoinDTO.getMemberId();
        boolean existMember = lsjShoesRepository.existsById(memberId);
        if (existMember) {
            throw new IdExistException();
        }

        // 중복이 아니니 회원 가입 처리하기.
//        ShoesMember shoesMember = modelMapper.map(shoesJoinDTO, ShoesMember.class);
        ShoesMember shoesMember = dtoToEntity(shoesJoinDTO);

        //패스워드는 현재 평문 -> 암호로 변경.
        shoesMember.changePassword(passwordEncoder.encode(shoesMember.getMemberPw()));
        // 역할 추가. 기본 USER
        shoesMember.addRole(ShoesRole.USER);

        // 데이터 가 잘 알맞게 변경이 됐는지 여부,
        log.info("joinMember: " + shoesMember);
        log.info("joinMember: " + shoesMember.getRoleSet());

        // 디비에 적용하기.
        lsjShoesRepository.save(shoesMember);

    }
}

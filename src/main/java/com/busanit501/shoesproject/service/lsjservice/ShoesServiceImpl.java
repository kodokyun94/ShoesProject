package com.busanit501.shoesproject.service.lsjservice;

import com.busanit501.shoesproject.domain.lsjdomain.Shoes;
import com.busanit501.shoesproject.domain.lsjdomain.ShoesRole;
import com.busanit501.shoesproject.dto.lsjdto.ShoesJoinDTO;
import com.busanit501.shoesproject.repository.lsjrepository.ShoesRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@Log4j2
@RequiredArgsConstructor
public class ShoesServiceImpl implements ShoesService {

    // 다른 기능들 도움 받기, 의존, 주입, 포함 관계 ,
    private final ModelMapper modelMapper;
    private final ShoesRepository shoesRepository;
    private final PasswordEncoder passwordEncoder;


    @Override
    public void join(ShoesJoinDTO shoesJoinDTO) throws MidExistException {
        //기존 아이디와 중복되는지 여부 확인
        String member_id = String.valueOf(shoesJoinDTO.getMember_id());
        boolean existMember = shoesRepository.existsById(member_id);
        if (existMember) {
            throw new MidExistException();
        }

        // 중복이 아니니 회원 가입 처리하기.
        Shoes shoes = modelMapper.map(shoesJoinDTO, Shoes.class);

        //패스워드는 현재 평문 -> 암호로 변경.
        shoes.changePassword(passwordEncoder.encode(shoes.getMember_pw()));
        // 역할 추가. 기본 USER
        shoes.addRole(ShoesRole.USER);

        // 데이터 가 잘 알맞게 변경이 됐는지 여부,
        log.info("joinMember: " + shoes);
        log.info("joinMember: " + shoes.getRoleSet());

        // 디비에 적용하기.
        shoesRepository.save(shoes);

    }
}

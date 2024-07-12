package com.busanit501.shoesproject.service.lsjservice;


import com.busanit501.shoesproject.domain.Member;
import com.busanit501.shoesproject.dto.lsjdto.ShoesJoinDTO;

public interface ShoesService2 {
    // 중복 아이디 예외처리
    static class IdExistException extends Exception {

    }

    void join(ShoesJoinDTO shoesJoinDTO) throws IdExistException;

    default Member dtoToEntity(ShoesJoinDTO shoesJoinDTO) {

        Member member = Member.builder()
                .memberId(shoesJoinDTO.getMemberId())
                .memberPw(shoesJoinDTO.getMemberPw())
                .memberName(shoesJoinDTO.getMemberName())
                .memberEmail(shoesJoinDTO.getMemberEmail())
                .memberPhone(shoesJoinDTO.getMemberPhone())
                .build();

        return member;
    }

//     entityToDTO
//     화면(DTO) ->  컨트롤러 ->서비스(각 변환작업을함.) - Entity 타입으로 - DB
    default ShoesJoinDTO entityToDto(Member member) {
        ShoesJoinDTO shoesJoinDTO = ShoesJoinDTO.builder()
                .memberId(member.getMemberId())
                .memberPw(member.getMemberPw())
                .memberName(member.getMemberName())
                .memberEmail(member.getMemberEmail())
                .memberPhone(member.getMemberPhone())
                .build();

        return shoesJoinDTO;
    }


}
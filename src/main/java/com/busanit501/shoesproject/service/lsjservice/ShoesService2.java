package com.busanit501.shoesproject.service.lsjservice;


import com.busanit501.shoesproject.domain.lsjdomain.ShoesMember;
import com.busanit501.shoesproject.dto.lsjdto.ShoesJoinDTO;

public interface ShoesService2 {
    // 중복 아이디 예외처리
    static class IdExistException extends Exception {

    }

    void join(ShoesJoinDTO shoesJoinDTO) throws IdExistException;

    default ShoesMember dtoToEntity(ShoesJoinDTO shoesJoinDTO) {

        ShoesMember shoesMember = ShoesMember.builder()
                .memberId(shoesJoinDTO.getMemberId())
                .memberPw(shoesJoinDTO.getMemberPw())
                .memberName(shoesJoinDTO.getMemberName())
                .memberEmail(shoesJoinDTO.getMemberEmail())
                .memberPhone(shoesJoinDTO.getMemberPhone())
                .build();

        return shoesMember;
    }

    // entityToDTO
    // 화면(DTO) ->  컨트롤러 ->서비스(각 변환작업을함.) - Entity 타입으로 - DB
//    default ShoesJoinDTO entityToDto(ShoesMember shoesMember) {
//        ShoesJoinDTO shoesJoinDTO = ShoesJoinDTO.builder()
//                .memberId(shoesMember.getMemberId())
//                .memberPw(shoesMember.getMemberPw())
//                .memberName(shoesMember.getMemberName())
//                .memberEmail(shoesMember.getMemberEmail())
//                .memberPhone(shoesMember.getMemberPhone())
//                .build();
//
//        return shoesJoinDTO;
//    }


}
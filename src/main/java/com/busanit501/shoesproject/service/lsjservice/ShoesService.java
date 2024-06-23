package com.busanit501.shoesproject.service.lsjservice;

import com.busanit501.shoesproject.domain.lsjdomain.Shoes;
import com.busanit501.shoesproject.dto.lsjdto.ShoesJoinDTO;

public interface ShoesService {
    // 중복 아이디 예외처리
    static class MidExistException extends Exception {

    }

    void join(ShoesJoinDTO shoesJoinDTO) throws MidExistException;

//    default Shoes dtoToEntity(ShoesJoinDTO shoesJoinDTO) {
//
//        Shoes shoes = Shoes.builder()
//                .member_id(shoesJoinDTO.getMember_id())
//                .member_pw(shoesJoinDTO.getMember_pw())
//                .member_name(shoesJoinDTO.getMember_name())
//                .build();
//
//        return shoes;
//    }
}
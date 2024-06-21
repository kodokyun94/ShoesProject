package com.busanit501.shoesproject.service.lsjservice;

import com.busanit501.shoesproject.domain.lsjdomain.Member;
import com.busanit501.shoesproject.dto.lsjdto.MemberJoinDTO;

public interface MemberService {
    // 중복 아이디 예외처리
    static class MidExistException extends Exception {

    }

    void join(MemberJoinDTO memberJoinDTO) throws MidExistException;

    default Member dtoToEntity(MemberJoinDTO memberJoinDTO) {

        Member member = Member.builder()
                .member_id(memberJoinDTO.getMember_id())
                .member_pw(memberJoinDTO.getMember_pw())
                .member_name(memberJoinDTO.getMember_name())
                .build();

        return member;
    }
}

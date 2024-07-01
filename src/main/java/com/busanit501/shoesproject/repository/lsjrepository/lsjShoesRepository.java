package com.busanit501.shoesproject.repository.lsjrepository;

import com.busanit501.shoesproject.domain.lsjdomain.ShoesMember;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

public interface lsjShoesRepository extends JpaRepository<ShoesMember, String> {

    // 소셜 로그인이 아닌, 일반 로그인으로 검색하기.
    // N+1,한번에 다같이 조회를 하자. in 연산자 이용해서, 하나의 쿼리로 동작하기.
    @EntityGraph(attributePaths = "roleSet")
    @Query("select s from ShoesMember s where s.memberId = :memberId and s.memberSocial = false")
    Optional<ShoesMember> getWithRoles(String memberId);

    // 이메일으로 유저 확인.
    @EntityGraph(attributePaths = "roleSet")
    Optional<ShoesMember> findByMemberEmail(String memberEmail);

    // mid로 유저 확인.
    @EntityGraph(attributePaths = "roleSet")
    Optional<ShoesMember> findByMemberId(String mid);

    //DML 적용하기
    @Modifying
    @Transactional
    @Query("update ShoesMember s set s.memberPw=:memberPw where s.memberId = :memberId")
    void updatePassword(@Param("memberPw") String memberPw, @Param("memberId") String memberId);

}

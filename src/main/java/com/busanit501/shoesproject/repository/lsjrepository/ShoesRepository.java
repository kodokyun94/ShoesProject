package com.busanit501.shoesproject.repository.lsjrepository;

import com.busanit501.shoesproject.domain.lsjdomain.Shoes;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface ShoesRepository extends JpaRepository<Shoes, String> {

    // 소셜 로그인이 아닌, 일반 로그인으로 검색하기.
    // N+1,한번에 다같이 조회를 하자. in 연산자 이용해서, 하나의 쿼리로 동작하기.
    @EntityGraph(attributePaths = "roleSet")
    @Query("select s from Shoes s where s.member_id = : id")
    Optional<Shoes> getWithRoles(String member_id);
}

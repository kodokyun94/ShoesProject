package com.busanit501.shoesproject.repository.lsjrepository;

import com.busanit501.shoesproject.domain.lsjdomain.LsjJoin;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JoinRepository extends JpaRepository<LsjJoin, Long> {

    LsjJoin findByEmail(String email);
}
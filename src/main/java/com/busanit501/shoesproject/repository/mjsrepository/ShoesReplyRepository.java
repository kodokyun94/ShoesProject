package com.busanit501.shoesproject.repository.mjsrepository;

import com.busanit501.shoesproject.domain.mjsdomain.Reply;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ShoesReplyRepository extends JpaRepository<Reply, Long> {
    }

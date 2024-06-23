package com.busanit501.shoesproject.repository.mjsrepository;

import com.busanit501.shoesproject.domain.mjsdomain.Reply;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ShoesReplyRepository extends JpaRepository<Reply, Long> {
    @Query("select r from Reply r where r.shoes.itemId = :itemId")
    Page<Reply> listOfBoard(Long itemId, Pageable pageable);

    //삭제 기능.
    void deleteByShoes_ItemId (Long itemId);

    //추가 기능.
    // 1번 게시글 board_bno 에 있는 모든 댓글 다 조회.
    List<Reply> findByShoesItemId(Long itemId);
}




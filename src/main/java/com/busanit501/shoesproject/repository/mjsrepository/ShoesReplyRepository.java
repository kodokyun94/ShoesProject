package com.busanit501.shoesproject.repository.mjsrepository;

import com.busanit501.shoesproject.domain.mjsdomain.Reply;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ShoesReplyRepository extends JpaRepository<Reply, Long> {
    @Query("select r from Reply r where r.shoes.item_id = :item_id")
    Page<Reply> listOfBoard(Long item_id, Pageable pageable);

    //삭제 기능.
    void deleteByShoes_item_id (Long item_id);

    //추가 기능.
    // 1번 게시글 board_bno 에 있는 모든 댓글 다 조회.
    List<Reply> findByShoesItem_id(Long item_id);

}

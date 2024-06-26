package com.busanit501.shoesproject.repository.mjsrepository;


import com.busanit501.shoesproject.domain.mjsdomain.Member2;
import com.busanit501.shoesproject.domain.mjsdomain.Reply;
import com.busanit501.shoesproject.domain.mjsdomain.Shoes;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@SpringBootTest
@Log4j2
public class ReplyRepositoryTest {
    @Autowired
    private ShoesReplyRepository shoesReplyRepository;

    @Test
    public void testInsert() {
        //실제 디비 각자 데이터에 따라서 다름.
        // 현재 bno = 900
        Long itemId = 100L;

        Shoes shoes = Shoes.builder()
                .itemId(itemId)
                .build();
        Member2 member2 = Member2.builder()
                .memberId("lsy")
                .build();

        Reply reply = Reply.builder()
                .shoes(shoes)
                .content("오늘 점심은 면으로")
                .member(member2)
                .rating(5)
                .build();

        shoesReplyRepository.save(reply);

    } //

    @Transactional
    @Test
    public void testBoardReplies() {
        // 각자 테이블의 데이터 내용에 맞게.
        Long itemId = 18L;

        Pageable pageable =
                PageRequest.of(0,10,
                        Sort.by("rno").descending());

//       Page<Reply> result = replyRepository.listOfBoard(bno,pageable);
        Optional<Reply> result = shoesReplyRepository.findById(itemId);
        List<Reply> result2 = shoesReplyRepository.findByShoesItemId(itemId);

//        Reply reply = result.orElseThrow();
        log.info("reply 확인 : " +result.isEmpty());
        log.info("reply2 확인 : " +result2.isEmpty());
//       result.getContent().forEach(reply -> {
//           log.info("reply 확인 : " +reply);
//       });

    }

}

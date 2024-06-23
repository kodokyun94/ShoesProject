package com.busanit501.shoesproject;

import com.busanit501.shoesproject.domain.nhjdomain.Review;
import com.busanit501.shoesproject.repository.nhjrepository.ReviewRepository;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;
import java.util.stream.IntStream;

@SpringBootTest
@Log4j2
public class ReviewRepositoryTest {
    @Autowired
    ReviewRepository reviewRepository;


    @Test
    public void testInsertItem() {
        IntStream.rangeClosed(1, 10).forEach(i -> {
                    Review review = Review.builder()
                            .item_id(123 +i)
                            .item_rank("별5"+i)
                            .item_rev("좋아요"+i)
                            .member_id(123+i)
                            .member_name("나호진"+i)
                            .build();

            reviewRepository.save(review);
                    log.info("추가한 BNO: " + reviewRepository);
                }
        );
    } // insetItem test

    @Test
    public void testSelect() {
        Long item_id = 100L;
        Optional<Review> result = reviewRepository.findById(item_id);
        Review review = result.orElseThrow();
        log.info("조회 결과 : " + review);
    }

    @Test
    public void testDelete() {
        Long item_id = 100L;
        // 반영.
        reviewRepository.deleteById(1L);
        log.info("조회 결과2 후: 디비상에서 삭제 여부 확인 하기.");
    }


//    @Test
//    public void testInsertMember() {
//        IntStream.rangeClosed(1, 50).forEach(i -> {
//                    Member member = Member.builder()
//                            .member_address("부산진구" +i)
//                            .member_email("dassf"+i+"@naver.com")
//                            .member_name("유명가수"+i)
//                            .member_phone("0101111111"+i)
//                            .member_pw("1234")
//                            .build();
//                    // 데이터베이스에 추가,
//                    // save 없으면, 1)추가, 있으면, 2) 수정.
//                     memberRepository.save(member);
//                    log.info("추가한 BNO: " + memberRepository);
//                }
//        );
//    } // insetMember test







}


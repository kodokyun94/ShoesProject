package com.busanit501.shoesproject.repository.mjsrepository;

import com.busanit501.shoesproject.domain.mjsdomain.Shoes;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.stream.IntStream;

@SpringBootTest
@Log4j2
public class ShoesRepositoyryTest {

    @Autowired
    ShoesRepository shoesRepository;

    @Test
    public void testInsert(){
        IntStream.rangeClosed(1, 100).forEach(i ->
        {
            Shoes shoes = Shoes.builder()
                    .item_brand("더미 브렌드"+i)
                    .item_type("더미 타입" +i)
                    .item_name("더미 이름"+i)
                    .item_price("더미 가격"+i)
                    .item_review_rank_avg("더미 평점"+i)
                    .item_gender("더미 성별")
                    .build();
            // 데이터베이스에 추가,
            // save 없으면, 1)추가, 있으면, 2) 수정.
            Shoes result = shoesRepository.save(shoes);
            log.info("추가한 Item_id: " + result.getItem_id());

        }
        );
    }

}

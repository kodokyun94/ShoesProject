package com.busanit501.shoesproject.service.mjsservice;


import com.busanit501.shoesproject.dto.mjsdot.ShoesDTO;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@Log4j2
public class ShoesServiceTest {
    @Autowired
    private ShoesService shoesService;

    @Test
    public void testInsert(){
        ShoesDTO shoesDTO = ShoesDTO.builder()
                .item_brand("더미 브렌드")
                .item_type("더미 타입" )
                .item_name("더미 이름")
                .item_price("더미 가격")
                .item_review_rank_avg("더미 평점")
                .item_gender("더미 성별")
                .build();


    }
}

package com.busanit501.shoesproject;

import com.busanit501.shoesproject.domain.nhjdomain.ShoesSize;
import com.busanit501.shoesproject.repository.nhjrepository.ShoesSizeRepository;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;
import java.util.stream.IntStream;

@SpringBootTest
@Log4j2
public class ShoesSizeRepositoryTest {
    @Autowired
    ShoesSizeRepository shoesSizeRepository;


    @Test
    public void testInsertItem() {
        IntStream.rangeClosed(1, 10).forEach(i -> {
                    ShoesSize shoesSize = ShoesSize.builder()
                            .size_id(230 + 5*i)
                            .item_size("230")

                            .build();

            shoesSizeRepository.save(shoesSize);
                    log.info("추가한 BNO: " + shoesSizeRepository);
                }
        );
    } // insetItem test
//
    @Test
    public void testSelect() {
        Long size_id = 1L;
        Optional<ShoesSize> result = shoesSizeRepository.findById(size_id);
        ShoesSize shoesSize = result.orElseThrow();
        log.info("조회 결과 : " + shoesSize);
    }
//
    @Test
    public void testDelete() {
        Long shoesSize = 1L;
        // 반영.
        shoesSizeRepository.deleteById(1L);
        log.info("조회 결과2 후: 디비상에서 삭제 여부 확인 하기.");
    }

}


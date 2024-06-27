package com.busanit501.shoesproject.repository.mjsrepository;

import com.busanit501.shoesproject.domain.mjsdomain.Shoes;
import jakarta.transaction.Transactional;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.util.List;
import java.util.Optional;
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
                    .itemBrand("더미 브렌드"+i)
                    .itemType("더미 타입" +i)
                    .itemName("더미 이름"+i)
                    .itemPrice(35000+i)
                    .itemReviewRankAvg("더미 평점"+i)
                    .itemGender("더미 성별")
                    .build();
            // 데이터베이스에 추가,
            // save 없으면, 1)추가, 있으면, 2) 수정.
            Shoes result = shoesRepository.save(shoes);
            log.info("추가한 ItemId: " + result.getItemId());

        }
        );
    }

    @Transactional
    @Test
    public void testSelect() {
        Long itemId = 100L;
        Optional<Shoes> result = shoesRepository.findById(itemId);
        Shoes shoes = result.orElseThrow();
        log.info("조회 결과 : " + shoes);
    }

    @Transactional
    @Test
    public void testUpdate() {
        Long itemId = 100L;
        Optional<Shoes> result = shoesRepository.findById(itemId);
        Shoes shoes = result.orElseThrow();

        log.info("조회 결과1 전 : " + shoes);
        shoes.changeall("신발","운동화","브랜드","10000원", "5","남성");
        // 반영.
        shoesRepository.save(shoes);
        log.info("조회 결과2 후: " + shoes);

    }


    @Test
    public void testDelete() {
        Long itemId = 100L;
        shoesRepository.deleteById(itemId);
        log.info("조회 결과2 후: 디비상에서 삭제 여부 확인 하기.");
    }

//    페이징 테스트
    @Transactional
    @Test
    public void testPaging() {
//   준비물 준비
        // 첫번째 파라미터 :페이지수(1페이지 , 0)
        // 두번째 파라미터 :페이지 당 보여줄 갯수(10개)
        // 세번째 파라미터 :정렬 기준, bno , 내림차순.

        Pageable pageable = PageRequest.of(0, 10,Sort.by("itemId").descending());
        // 10개씩 조회 해보기.
        //Page 타입이라는 것은, 해당 결과에, 여러 정보들이 있음.
        // 예) 10개씩 가져온 데이터, 2)페이지 정보, 3)갯수, 4)전체 갯수 등.
        Page<Shoes> result = shoesRepository.findAll(pageable);

        // 담겨진 페이징 관련 결과를 출력및 알아보기.
        log.info("전체 갯수 total  result.getTotalElements() : " + result.getTotalElements());
        log.info("전체 페이지  result.getTotalPages() : " + result.getTotalPages());
        log.info("페이지 number  result.getNumber() : " + result.getNumber());
        log.info("페이지 당 불러올 수  result.getSize() : " + result.getSize());
        log.info("불러올 데이터 목록  result.getContent() : ");
        // 불러올 목록 데이터를 받아서 처리해보기.
        List<Shoes> list = result.getContent();
        list.forEach(shoes -> log.info(shoes));

    }


    @Test
    public void testSearch() {

        Pageable pageable = PageRequest.of(1, 10, Sort.by("itemId").descending());
        // 실행 여부를 확인 해보기.
        shoesRepository.search(pageable);
    }

}

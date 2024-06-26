package com.busanit501.shoesproject.service.mjsservice;


import com.busanit501.shoesproject.dto.mjsdto.*;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;

@SpringBootTest
@Log4j2
public class ShoesService2Test {
    @Autowired
    private ShoesService shoesService;

    @Test
    public void testInsert(){
        ShoesDTO shoesDTO = ShoesDTO.builder()
                .itemBrand("더미 브렌드")
                .itemType("더미 타입" )
                .itemName("더미 이름")
                .itemPrice("더미 가격")
                .itemReviewRankAvg("더미 평점")
                .itemGender("더미 성별")
                .build();


    }
    @Test
    public void testList() {
        // 화면에서 전달할 내용을 담은 PageRequestDTO 더미가 필요.
        PageRequestDTO pageRequestDTO = PageRequestDTO.builder()
                .type("tbpgrn")
                .keyword("더미")
                .page(1)
                .size(10)
                .build();

        PageResponseDTO<ShoesListAllDTO> responseDTO = shoesService.listWithAll(pageRequestDTO);
        log.info("list 테스트 responseDTO : " + responseDTO);

    }

    @Test
    public void testRegisterWithImages() {
        ShoesDTO shoesDTO = ShoesDTO.builder()
                .itemBrand("더미 브렌드")
                .itemType("더미 타입" )
                .itemName("더미 이름")
                .itemPrice("더미 가격")
                .itemReviewRankAvg("더미 평점")
                .itemGender("더미 성별")
                .build();

        // 더미 이미지들
        shoesDTO.setFileNames(
                Arrays.asList(
                        //파일명,
                        UUID.randomUUID()+"_testImage.png",
                        UUID.randomUUID()+"_testImage2.png",
                        UUID.randomUUID()+"_testImage3.png"
                )
        ); // 더미 이미지 파일명 추가

        Long itemId = shoesService.register(shoesDTO);
        log.info("boardService, register 확인 bno : " + itemId);
    }

    @Test
    public void deleteAll() {
        shoesService.deleteAll(99L);

    }
    @Test
    public void testListAll() {
        // 화면에서 전달할 내용을 담은 PageRequestDTO 더미가 필요.
        PageRequestDTO pageRequestDTO = PageRequestDTO.builder()
                .type("tbpgrn")
                .keyword("더미")
                .page(1)
                .size(10)
                .build();

        PageResponseDTO<ShoesListAllDTO> responseDTO =
                shoesService.listWithAll(pageRequestDTO);

        // 이미지들 만 뽑아 보기..
        List<ShoesListAllDTO> dtoList = responseDTO.getDtoList();
        dtoList.forEach(shoesListAllDTO -> {
            log.info("이름 : " + shoesListAllDTO.getItemName());
            if(shoesListAllDTO.getShoesImages() !=null ) {
                for (ShoesImageDTO shoesImageDTO : shoesListAllDTO.getShoesImages()) {
                    log.info("이미지들 목록중에서 하나씩 꺼내서 파일명 조회 : " + shoesImageDTO);
                }
            }
        });

        log.info("testListAll 테스트 responseDTO : " + responseDTO);

    }


}

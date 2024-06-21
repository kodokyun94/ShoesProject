package com.busanit501.shoesproject.service.mjsservice;


import com.busanit501.shoesproject.domain.mjsdomain.Shoes;
import com.busanit501.shoesproject.dto.mjsdto.*;

import java.util.List;
import java.util.stream.Collectors;

public interface ShoesService {
    Long register(ShoesDTO shoesDTO);
    ShoesDTO read(Long item_id);
    void update(ShoesDTO shoesDTO);
    void delete(Long item_id);
    void deleteAll(Long item_id);

    PageResponseDTO<ShoesListAllDTO> listWithAll(PageRequestDTO pageRequestDTO);

    default Shoes dtoToEntity(ShoesDTO shoesDTO){

        Shoes shoes = Shoes.builder()
                .item_id(shoesDTO.getItem_id())
                .item_name(shoesDTO.getItem_name())
                .item_brand(shoesDTO.getItem_brand())
                .item_price(shoesDTO.getItem_price())
                .item_type(shoesDTO.getItem_type())
                .item_gender(shoesDTO.getItem_gender())
                .item_review_rank_avg(shoesDTO.getItem_review_rank_avg())
                .build();

        // 첨부 이미지들이 추가
        if(shoesDTO.getFileNames() != null) {
            shoesDTO.getFileNames().forEach(fileName ->
            {
                String[] arr = fileName.split("_");
                shoes.addImage(arr[0],arr[1]);
            }); // forEach 닫기
        } // if 닫기
        return shoes;
    } // dtoToEntity 닫기.

    // entityToDTO
    // 화면(DTO) ->  컨트롤러 ->서비스(각 변환작업을함.) - Entity 타입으로 - DB
    default ShoesDTO entityToDTO(Shoes shoes) {
        ShoesDTO shoesDTO = ShoesDTO.builder()
                .item_id(shoes.getItem_id())
                .item_name(shoes.getItem_name())
                .item_type(shoes.getItem_type())
                .item_brand(shoes.getItem_brand())
                .item_price(shoes.getItem_price())
                .item_gender(shoes.getItem_gender())
                .item_review_rank_avg(shoes.getItem_review_rank_avg())
                .build();

        // 첨부된 이미지 파일들.
        List<String> fileNames = shoes.getImageSet().stream().sorted().map(
                shoesImage -> shoesImage.getUuid()+"_"+shoesImage.getFileName()
        ).collect(Collectors.toList());

        shoesDTO.setFileNames(fileNames);

        return shoesDTO;
    }
}

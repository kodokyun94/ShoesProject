package com.busanit501.shoesproject.service.mjsservice;


import com.busanit501.shoesproject.domain.mjsdomain.Shoes;
import com.busanit501.shoesproject.dto.mjsdto.*;

import java.util.List;
import java.util.stream.Collectors;

public interface ShoesService {
    Long register(ShoesDTO shoesDTO);
    ShoesDTO read(Long itemId);
    void update(ShoesDTO shoesDTO);
    void delete(Long itemId);
    void deleteAll(Long itemId);

    PageResponseDTO<ShoesListAllDTO> listWithAll(PageRequestDTO pageRequestDTO);

    default Shoes dtoToEntity(ShoesDTO shoesDTO){

        Shoes shoes = Shoes.builder()
                .itemId(shoesDTO.getItemId())
                .itemName(shoesDTO.getItemName())
                .itemBrand(shoesDTO.getItemBrand())
                .itemPrice(shoesDTO.getItemPrice())
                .itemType(shoesDTO.getItemType())
                .itemGender(shoesDTO.getItemGender())
                .itemReviewRankAvg(shoesDTO.getItemReviewRankAvg())
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
                .itemId(shoes.getItemId())
                .itemName(shoes.getItemName())
                .itemType(shoes.getItemType())
                .itemBrand(shoes.getItemBrand())
                .itemPrice(shoes.getItemPrice())
                .itemGender(shoes.getItemGender())
                .itemReviewRankAvg(shoes.getItemReviewRankAvg())
                .build();

        // 첨부된 이미지 파일들.
        List<String> fileNames = shoes.getImageSet().stream().sorted().map(
                shoesImage -> shoesImage.getUuid()+"_"+shoesImage.getFileName()
        ).collect(Collectors.toList());

        shoesDTO.setFileNames(fileNames);

        return shoesDTO;
    }
}

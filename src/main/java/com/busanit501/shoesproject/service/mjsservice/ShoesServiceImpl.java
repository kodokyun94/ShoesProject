package com.busanit501.shoesproject.service.mjsservice;

import com.busanit501.shoesproject.domain.mjsdomain.Reply;
import com.busanit501.shoesproject.domain.mjsdomain.Shoes;
import com.busanit501.shoesproject.dto.mjsdto.PageRequestDTO;
import com.busanit501.shoesproject.dto.mjsdto.PageResponseDTO;
import com.busanit501.shoesproject.dto.mjsdto.ShoesDTO;
import com.busanit501.shoesproject.dto.mjsdto.ShoesListAllDTO;
import com.busanit501.shoesproject.repository.mjsrepository.ShoesReplyRepository;
import com.busanit501.shoesproject.repository.mjsrepository.ShoesRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Log4j2
@RequiredArgsConstructor
@Transactional
public class ShoesServiceImpl implements ShoesService {
    private final ShoesRepository shoesRepository;
    private final ModelMapper modelMapper;
    private final ShoesReplyRepository shoesReplyRepository;

    @Override
    public Long register(ShoesDTO shoesDTO) {
      Shoes shoes = dtoToEntity(shoesDTO);
      Long itemId = shoesRepository.save(shoes).getItemId();

        return itemId;
    }

    @Override
    public ShoesDTO read(Long itemId) {
        Optional<Shoes> result = shoesRepository.findById(itemId);
        Shoes shoes = result.orElseThrow();
        ShoesDTO shoesDTO = entityToDTO(shoes);
        return shoesDTO;
    }

    @Override
    public void update(ShoesDTO shoesDTO) {
        Optional<Shoes> result = shoesRepository.findById(shoesDTO.getItemId());
        Shoes shoes = result.orElseThrow();
        shoes.changeall(shoes.getItemName(),shoes.getItemBrand(),shoes.getItemType(),shoes.getItemPrice(),shoes.getItemReviewRankAvg(),shoes.getItemGender());
        shoes.clearImages();
        if(shoesDTO.getFileNames() !=null){
            for(String fileName : shoesDTO.getFileNames()){
                String[] arr = fileName.split("_");
                shoes.addImage(arr[0], arr[1]);
            }
        }
        shoesRepository.save(shoes);

    }

    @Override
    public void delete(Long itemId) {
        shoesRepository.deleteById(itemId);
    }


    @Override
    public void deleteAll(Long itemId) {

        List<Reply> result = shoesReplyRepository.findByShoesItemId(itemId);
        log.info("result.isEmpty() 값  확인:" + result.isEmpty());
        boolean checkReply = result.isEmpty() ? false : true;
        log.info("result.isEmpty() 값  확인2 checkReply:" + checkReply);
        if(checkReply){
            shoesReplyRepository.deleteByShoes_ItemId(itemId);
        }

        shoesRepository.deleteById(itemId);
    }

    @Override
    public PageResponseDTO<ShoesListAllDTO> listWithAll(PageRequestDTO pageRequestDTO) {
        String[] types = pageRequestDTO.getTypes();
        String keyword = pageRequestDTO.getKeyword();
        Pageable pageable = pageRequestDTO.getPageable("itemId");

        Page<ShoesListAllDTO> result = shoesRepository.searchWithAll(types,keyword,pageable);

        PageResponseDTO<ShoesListAllDTO> pageResponseDTO =
                PageResponseDTO.<ShoesListAllDTO>withAll()
                        .pageRequestDTO(pageRequestDTO)
                        .dtoList(result.getContent())
                        .total((int) result.getTotalElements())
                        .build();

        return pageResponseDTO;
    }
}

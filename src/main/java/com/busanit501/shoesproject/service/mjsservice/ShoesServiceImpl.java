package com.busanit501.shoesproject.service.mjsservice;

import com.busanit501.shoesproject.domain.mjsdomain.Shoes;
import com.busanit501.shoesproject.dto.mjsdto.ShoesDTO;
import com.busanit501.shoesproject.repository.mjsrepository.ShoesReplyRepository;
import com.busanit501.shoesproject.repository.mjsrepository.ShoesRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
        Shoes shoes = modelMapper.map(shoesDTO, Shoes.class);
        Long item_id = shoesRepository.save(shoes).getItem_id();

        return item_id;
    }

    @Override
    public ShoesDTO read(Long item_id) {
        Optional<Shoes> result = shoesRepository.findById(item_id);
        Shoes shoes = result.orElseThrow();
        ShoesDTO shoesDTO = modelMapper.map(shoes, ShoesDTO.class);
        return shoesDTO;
    }

    @Override
    public void update(ShoesDTO shoesDTO) {
        Optional<Shoes> result = shoesRepository.findById(shoesDTO.getItem_id());
        Shoes shoes = result.orElseThrow();

    }

    @Override
    public void delete(Long item_id) {
        shoesRepository.deleteById(item_id);
    }
}

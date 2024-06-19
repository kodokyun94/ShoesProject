package com.busanit501.shoesproject.service.mjsservice;

import com.busanit501.shoesproject.domain.mjsdomain.Shoes;
import com.busanit501.shoesproject.dto.mjsdot.ShoesDTO;
import com.busanit501.shoesproject.repository.mjsrepository.ShoesRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Log4j2
@RequiredArgsConstructor
@Transactional
public class ShoesServiceImpl implements ShoesService {
    private final ShoesRepository shoesRepository;
    private final ModelMapper modelMapper;

    @Override
    public Long register(ShoesDTO shoesDTO) {
        Shoes shoes = modelMapper.map(shoesDTO, Shoes.class);
        Long item_id = shoesRepository.save(shoes).getItem_id();

        return item_id;
    }

    @Override
    public ShoesDTO read(Long item_id) {
        return null;
    }

    @Override
    public void update(ShoesDTO shoesDTO) {

    }

    @Override
    public void delete(Long item_id) {

    }
}

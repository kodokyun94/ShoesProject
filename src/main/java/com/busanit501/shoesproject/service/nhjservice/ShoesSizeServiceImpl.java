package com.busanit501.shoesproject.service.nhjservice;

import com.busanit501.shoesproject.domain.nhjdomain.ShoesSize;
import com.busanit501.shoesproject.dto.nhjdto.ShoesSizeDTO;
import com.busanit501.shoesproject.repository.nhjrepository.ShoesSizeRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.Optional;


@Service
@Log4j2
@RequiredArgsConstructor
// all or nothing,
@Transactional
public class ShoesSizeServiceImpl implements ShoesSizeService {
  private final ShoesSizeRepository shoesSizeRepository;
  private final ModelMapper modelMapper;

  @Override
  public Long register(ShoesSizeDTO shoesSizeDTO) {
    ShoesSize shoesSize = modelMapper.map(shoesSizeDTO, ShoesSize.class);
    Long size_id = shoesSizeRepository.save(shoesSize).getSize_id();
    return size_id;
  }

  @Override
  public ShoesSizeDTO read(Long size_id) {
    Optional<ShoesSize> result = shoesSizeRepository.findById(size_id);
    ShoesSize shoesSize = result.orElseThrow();
    ShoesSizeDTO shoesSizeDTO = modelMapper.map(shoesSize, ShoesSizeDTO.class);

    return shoesSizeDTO;
  }

  @Override
  public void update(ShoesSizeDTO shoesSizeDTO) {
    Optional<ShoesSize> result = shoesSizeRepository.findById(shoesSizeDTO.getSize_id());
    ShoesSize shoesSize = result.orElseThrow();

  }

  @Override
  public void delete(Long size_id) {
    shoesSizeRepository.deleteById(size_id);
  }
}

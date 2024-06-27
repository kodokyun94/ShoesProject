package com.busanit501.shoesproject.service.nhjservice;

import com.busanit501.shoesproject.domain.kdkdomain.CartItem;
import com.busanit501.shoesproject.domain.nhjdomain.Size;
import com.busanit501.shoesproject.repository.kdkrepository.CartItemRepository;
import com.busanit501.shoesproject.repository.nhjrepository.SizeRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Log4j2
@Service
@RequiredArgsConstructor
public class SizeServiceImpl implements SizeService {

  private final SizeRepository sizeRepository;
  private final CartItemRepository cartItemRepository;

//  @Override
//  public Size getOne(Integer cartId) {
//    cartItemRepository.findCartDetailDtoList()
//    Optional<Size> sizeOptional = cartItemRepository.findById();
//    return null;
//  }

  @Override
  public List<Size> getAllSizes() {
    return sizeRepository.findAll();
  }


}

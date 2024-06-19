package com.busanit501.shoesproject.service.mjsservice;

import com.busanit501.shoesproject.dto.mjsdot.ShoesDTO;

public interface ShoesService {
    Long register(ShoesDTO shoesDTO);
    ShoesDTO read(Long item_id);
    void update(ShoesDTO shoesDTO);
    void delete(Long item_id);
}

package com.busanit501.shoesproject.service.nhjservice;

import com.busanit501.shoesproject.dto.nhjdto.ShoesSizeDTO;

public interface ShoesSizeService {
    Long register(ShoesSizeDTO shoesSizeDTO);
    ShoesSizeDTO read(Long size_id);
    void update(ShoesSizeDTO shoesSizeDTO);
    void delete(Long size_id);

}
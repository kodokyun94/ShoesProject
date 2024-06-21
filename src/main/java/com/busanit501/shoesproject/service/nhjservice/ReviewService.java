package com.busanit501.shoesproject.service.nhjservice;

import com.busanit501.shoesproject.dto.nhjdto.ReviewDTO;

public interface ReviewService {
    Long register(ReviewDTO reviewDTO);
    ReviewDTO read(Long size_id);
    void update(ReviewDTO reviewDTO);
    void delete(Long item_id);

}
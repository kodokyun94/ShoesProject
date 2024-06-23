package com.busanit501.shoesproject.service.nhjservice;

import com.busanit501.shoesproject.domain.nhjdomain.Review;
import com.busanit501.shoesproject.dto.nhjdto.ReviewDTO;

import java.util.List;

public interface ReviewService {

    void saveReview(ReviewDTO reviewDTO);

    List<Review> getAllReviews();

}
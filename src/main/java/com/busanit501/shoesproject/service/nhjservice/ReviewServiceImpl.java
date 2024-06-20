package com.busanit501.shoesproject.service.nhjservice;

import com.busanit501.shoesproject.domain.nhjdomain.Review;
import com.busanit501.shoesproject.dto.nhjdto.ReviewDTO;
import com.busanit501.shoesproject.repository.nhjrepository.ReviewRepository;
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
public class ReviewServiceImpl implements ReviewService {
  // 의존성 주입
  private final ReviewRepository reviewRepository;
  //  private final ReplyRepository replyRepository;
  private final ModelMapper modelMapper;

  @Override
  public Long register(ReviewDTO reviewDTO) {
    Review review = modelMapper.map(reviewDTO, Review.class);
    Long item_id = reviewRepository.save(review).getItem_id();
    return item_id;
  }

  @Override
  public ReviewDTO read(Long item_id) {
    Optional<Review> result = reviewRepository.findById(item_id);
    Review review = result.orElseThrow();
    ReviewDTO reviewDTO = modelMapper.map(review, ReviewDTO.class);

    return reviewDTO;
  }

  @Override
  public void update(ReviewDTO reviewDTO) {
    Optional<Review> result = reviewRepository.findById(reviewDTO.getItem_id());
    Review review = result.orElseThrow();

  }

  @Override
  public void delete(Long item_id) {
    reviewRepository.deleteById(item_id);
  }
}

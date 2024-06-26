package com.busanit501.shoesproject.service.nhjservice;

import com.busanit501.shoesproject.domain.nhjdomain.Review;
import com.busanit501.shoesproject.dto.nhjdto.ReviewDTO;
import com.busanit501.shoesproject.repository.nhjrepository.ReviewRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.UUID;

@Service
@Log4j2
@RequiredArgsConstructor
public class ReviewServiceImpl implements ReviewService {

  private final ReviewRepository reviewRepository;

  @Override
  public void saveReview(ReviewDTO reviewDTO) {
    MultipartFile file = reviewDTO.getFile();
    String imagePath = null;

    try {
      if (file != null && !file.isEmpty()) {
        // 파일 이름 생성
        String fileName = UUID.randomUUID() + "_" + file.getOriginalFilename();

        // 파일 저장 디렉토리
        String uploadDir = "c:\\upload\\reviewTest";

        // 파일 객체 생성
        File dest = new File(uploadDir, fileName);

        // 디렉토리가 존재하지 않으면 생성
        if (!dest.getParentFile().exists()) {
          dest.getParentFile().mkdirs();
        }

        // 파일 저장
        file.transferTo(dest);

        // 저장된 파일 경로
        imagePath = dest.getAbsolutePath();
      }

      Review review = Review.builder()
              .rating(reviewDTO.getRating())
              .content(reviewDTO.getContent())
              .imagePath(imagePath)
              .build();

      reviewRepository.save(review);
    } catch (IOException e) {
      e.printStackTrace(); // 에러 메시지 출력
    }
  }

  @Override
  public List<Review> getAllReviews() {
    return reviewRepository.findAll();
  }

}

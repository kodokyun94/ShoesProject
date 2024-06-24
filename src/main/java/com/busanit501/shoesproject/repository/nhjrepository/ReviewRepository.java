package com.busanit501.shoesproject.repository.nhjrepository;


import com.busanit501.shoesproject.domain.nhjdomain.Review;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReviewRepository extends JpaRepository<Review, Long> {


}

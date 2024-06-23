package com.busanit501.shoesproject.repository.nhjrepository;

import com.busanit501.shoesproject.domain.nhjdomain.ShoesColor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ShoesColorRepository extends JpaRepository<ShoesColor, Long> {

}

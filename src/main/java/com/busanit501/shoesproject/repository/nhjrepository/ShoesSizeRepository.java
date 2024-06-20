package com.busanit501.shoesproject.repository.nhjrepository;

import com.busanit501.shoesproject.domain.nhjdomain.ShoesSize;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ShoesSizeRepository extends JpaRepository<ShoesSize, Long> {

}

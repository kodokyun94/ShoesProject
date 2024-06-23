package com.busanit501.shoesproject.repository.nhjrepository;

import com.busanit501.shoesproject.domain.nhjdomain.Size;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SizeRepository extends JpaRepository<Size, Long> {

    List<Size> findAll();

}

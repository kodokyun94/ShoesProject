package com.busanit501.shoesproject.repository.mjsrepository.search;


import com.busanit501.shoesproject.domain.mjsdomain.Shoes;
import com.busanit501.shoesproject.dto.mjsdto.ShoesListAllDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ShoesSearch {
  Page<Shoes> search(Pageable pageable);



  Page<ShoesListAllDTO> searchWithAll(
          String[] types, String keyword ,Pageable pageable
  );

}














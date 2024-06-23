package com.busanit501.shoesproject.repository.mjsrepository;

import com.busanit501.shoesproject.domain.mjsdomain.Shoes;
import com.busanit501.shoesproject.repository.mjsrepository.search.ShoesSearch;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface ShoesRepository extends JpaRepository<Shoes, Long>, ShoesSearch {

    @EntityGraph(attributePaths = {"imageSet"})
    @Query("select s from Shoes s where s.itemId=:itemId")
    Optional<Shoes> findByIdWithImages(Long itemId);


}

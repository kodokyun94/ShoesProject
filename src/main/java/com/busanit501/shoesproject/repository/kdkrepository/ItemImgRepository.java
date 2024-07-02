package com.busanit501.shoesproject.repository.kdkrepository;


import com.busanit501.shoesproject.domain.kdkdomain.ItemImg;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ItemImgRepository extends JpaRepository<ItemImg, Long> {

    List<ItemImg> findByItemItemIdOrderByIdAsc(Long itemId);

    ItemImg findByItemItemIdAndRepimgYn(Long itemId, String repimgYn);

}
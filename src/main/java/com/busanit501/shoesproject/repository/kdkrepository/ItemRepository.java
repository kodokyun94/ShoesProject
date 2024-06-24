package com.busanit501.shoesproject.repository.kdkrepository;

import com.busanit501.shoesproject.domain.kdkdomain.Item;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ItemRepository extends JpaRepository<Item,Long> {

    Item findByItemId(Long itemId);

}

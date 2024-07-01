package com.busanit501.shoesproject.repository.kdkrepository;

import com.busanit501.shoesproject.domain.kdkdomain.Item;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ItemRepository extends JpaRepository<Item,Long>,
        QuerydslPredicateExecutor<Item>, ItemRepositoryCustom  {

    List<Item> findByItemName(String itemName);

    List<Item> findByItemNameOrItemDetail(String itemName, String itemDetail);

    List<Item> findByItemPriceLessThan(Integer price);

    List<Item> findByItemPriceLessThanOrderByItemPriceDesc(Integer price);

    @Query("select i from Item i where i.itemDetail like " +
            "%:itemDetail% order by i.itemPrice desc")
    List<Item> findByItemDetail(@Param("itemDetail") String itemDetail);

    @Query(value="select * from item i where i.item_detail like " +
            "%:itemDetail% order by i.price desc", nativeQuery = true)
    List<Item> findByItemDetailByNative(@Param("itemDetail") String itemDetail);

}

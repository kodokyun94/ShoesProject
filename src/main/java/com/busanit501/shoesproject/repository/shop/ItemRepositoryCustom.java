package com.busanit501.shoesproject.repository.shop;


import com.busanit501.shoesproject.domain.Item;
import com.busanit501.shoesproject.dto.shop.ItemSearchDTO;
import com.busanit501.shoesproject.dto.MainItemDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ItemRepositoryCustom {

    Page<Item> getAdminItemPage(ItemSearchDTO itemSearchDto, Pageable pageable);

    Page<MainItemDto> getMainItemPage(ItemSearchDTO itemSearchDto, Pageable pageable);

}
package com.busanit501.shoesproject.repository;


import com.busanit501.shoesproject.domain.Item;
import com.busanit501.shoesproject.dto.ItemSearchDto;
import com.busanit501.shoesproject.dto.MainItemDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ItemRepositoryCustom {

    Page<Item> getAdminItemPage(ItemSearchDto itemSearchDto, Pageable pageable);

    Page<MainItemDto> getMainItemPage(ItemSearchDto itemSearchDto, Pageable pageable);

}
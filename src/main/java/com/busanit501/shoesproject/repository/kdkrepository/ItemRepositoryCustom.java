package com.busanit501.shoesproject.repository.kdkrepository;



import com.busanit501.shoesproject.domain.kdkdomain.Item;
import com.busanit501.shoesproject.dto.kdkdto.ItemSearchDto;
import com.busanit501.shoesproject.dto.kdkdto.MainItemDto;
import jdk.jfr.Registered;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ItemRepositoryCustom {

    Page<Item> getAdminItemPage(ItemSearchDto itemSearchDto, Pageable pageable);

    Page<MainItemDto> getMainItemPage(ItemSearchDto itemSearchDto, Pageable pageable);

}
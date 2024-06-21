package com.busanit501.shoesproject.service;

import com.busanit501.shoesproject.dto.kdkdto.CartDTO;
import com.busanit501.shoesproject.repository.kdkrepository.ItemRepository;
import com.busanit501.shoesproject.service.kdkservice.CartService;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@Log4j2
public class ServiceTests {
    @Autowired
    private CartService cartService;
    @Autowired
    private ItemRepository itemRepository;
    @Autowired
    private ModelMapper modelMapper;

    @Test
    public void testCartInsert() {
        CartDTO cartDTO = CartDTO.builder()
                .cart_Id(1L)
                .item_id(1L)
                .build();
        Long rno = cartService.register(cartDTO);
        log.info("번호 : " + rno);
    }

    @Test
    public void testDelete() {
        cartService.delete(1L);
    }

}

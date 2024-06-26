package com.busanit501.shoesproject.service.kdkservice;

import com.busanit501.shoesproject.domain.kdkdomain.Cart;
import com.busanit501.shoesproject.domain.kdkdomain.Item;
import com.busanit501.shoesproject.domain.kdkdomain.Member;
import com.busanit501.shoesproject.dto.kdkdto.CartDTO;
import com.busanit501.shoesproject.dto.kdkdto.CartItemDTO;
import com.busanit501.shoesproject.repository.kdkrepository.CartRepository;
import com.busanit501.shoesproject.repository.kdkrepository.ItemRepository;
import com.busanit501.shoesproject.repository.kdkrepository.MemberRepository;
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
    @Autowired
    private MemberRepository memberRepository;
    @Autowired
    private CartRepository cartRepository;

    @Test
    public void testCartItemInsert() {

        Item item = itemRepository.findByItemId(2L);
        Cart cart = cartRepository.findByMember_MemberId(2L);


        log.info("Inserting item : " + item);

        CartItemDTO cartItemDTO = CartItemDTO.builder()
                .cart(cart)
                .item(item)
                .build();
//        log.info("Inserting cartItemDTO : " + cartItemDTO);

        Long rno = cartService.addCart(cartItemDTO,"dassf1@naver.com");
        log.info("번호 : " + rno);
    }

    @Test
    public void testDelete() {
        cartService.deleteCartItem(1L);
    }

}

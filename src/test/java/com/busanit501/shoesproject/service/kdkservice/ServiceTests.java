package com.busanit501.shoesproject.service.kdkservice;

import com.busanit501.shoesproject.repository.kdkrepository.CartRepository;
import com.busanit501.shoesproject.repository.kdkrepository.ItemRepository;
import com.busanit501.shoesproject.repository.lsjrepository.lsjShoesRepository;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

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
    private lsjShoesRepository memberRepository;
    @Autowired
    private CartRepository cartRepository;

    @Test
    @Transactional
    public void testCartItemInsert() {

//        Cart cart = cartRepository.findByMember_MemberId(1L);
//
////        log.info("testCartItemInsert item : " + item);
////        log.info("testCartItemInsert cart : " + cart);
//
//        CartItemDTO cartItemDTO = CartItemDTO.builder()
//                .cart(cart)
//                .itemId(4L)
//                .count(3)
//                .build();
//        log.info("testCartItemInsert Inserting cartItemDTO : " + cartItemDTO);
//
//        Long cartItemId = cartService.addCartItem(cartItemDTO,me);
//        log.info("cartItemId번호 : " + cartItemId);
    }

//    @Test
//    public void testCartItemList() {
//        Optional<ShoesMember> member = memberRepository.findByMemberEmail("123@naver.com");
//        Item item = itemRepository.findByItemId(3L);
//
//        CartItemDTO cartItemDTO1 = CartItemDTO.builder()
//                .itemId(item.getItemId())
//                .count(1)
//                .build();
//        cartService.addCartItem(cartItemDTO1,member.get().getMemberEmail());
//
//        List<CartDetailDTO> cartDetailDtoList = cartService.getCartList(member.());
//        log.info("cartDetailDtoList : " + cartDetailDtoList);
//  }

    @Test
    public void testDeleteCartItem() {
        cartService.deleteCartItem(1L);
    }

}

package com.busanit501.shoesproject.service.kdkservice;

import com.busanit501.shoesproject.domain.kdkdomain.Cart;
import com.busanit501.shoesproject.domain.kdkdomain.CartItem;
import com.busanit501.shoesproject.domain.kdkdomain.Item;
import com.busanit501.shoesproject.domain.kdkdomain.Member;
import com.busanit501.shoesproject.dto.kdkdto.CartDetailDTO;
import com.busanit501.shoesproject.dto.kdkdto.CartItemDTO;
import com.busanit501.shoesproject.repository.kdkrepository.CartItemRepository;
import com.busanit501.shoesproject.repository.kdkrepository.CartRepository;
import com.busanit501.shoesproject.repository.kdkrepository.ItemRepository;
import com.busanit501.shoesproject.repository.kdkrepository.MemberRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.thymeleaf.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class CartService {

    private final ItemRepository itemRepository;
    private final MemberRepository memberRepository;
    private final CartRepository cartRepository;
    private final CartItemRepository cartItemRepository;
//    private final OrderService orderService;

    public Long addCart(CartItemDTO cartItemDto, String memberEmail){

        Item item = itemRepository.findByItemId(cartItemDto.getItemId());

        Member member = memberRepository.findByMemberEmail(memberEmail);

        Cart cart = cartRepository.findByMember_MemberId(member.getMemberId());


        if(cart == null){
            cart = Cart.createCart(member);
            cartRepository.save(cart);
        }

        CartItem savedCartItem = cartItemRepository.findByCart_CartIdAndItem_ItemId(cart.getCartId(), item.getItemId());

        if(savedCartItem != null){
            savedCartItem.addCount(cartItemDto.getCount());
            return savedCartItem.getCartItemId();
        } else {
            CartItem cartItem = CartItem.createCartItem(cart, item, cartItemDto.getCount());
            cartItemRepository.save(cartItem);
            return cartItem.getCartItemId();
        }
    }

    @Transactional(readOnly = true)
    public List<CartDetailDTO> getCartList(String memberEmail){

        List<CartDetailDTO> cartDetailDTOList = new ArrayList<>();

        Member member = memberRepository.findByMemberEmail(memberEmail);

        Cart cart = cartRepository.findByMember_MemberId(member.getMemberId());

        if(cart == null){
            return cartDetailDTOList;
        }

        cartDetailDTOList = cartItemRepository.findCartDetailDtoList(cart.getCartId());
        return cartDetailDTOList;
    }



    @Transactional(readOnly = true)
    public boolean validateCartItem(Long cartItemId, String memberEmail){
        Member curMember = memberRepository.findByMemberEmail(memberEmail);

        CartItem cartItem = cartItemRepository.findByCart_CartIdAndItem_ItemId(cartItemId, cartItemId);

        Member savedMember = cartItem.getCart().getMember();

        if(!StringUtils.equals(curMember.getMemberId(), savedMember.getMemberId())){
            return false;
        }

        return true;
    }


    public void updateCartItemCount(Long cartItemId, int count){
        CartItem cartItem = cartItemRepository.findById(cartItemId)
                .orElseThrow(EntityNotFoundException::new);

        cartItem.updateCount(count);
    }

    public void deleteCartItem(Long cartItemId) {
        CartItem cartItem = cartItemRepository.findById(cartItemId)
                .orElseThrow(EntityNotFoundException::new);
        cartItemRepository.delete(cartItem);
    }

//    public Long orderCartItem(List<CartOrderDto> cartOrderDtoList, String email){
//        List<OrderDto> orderDtoList = new ArrayList<>();
//
//        for (CartOrderDto cartOrderDto : cartOrderDtoList) {
//            CartItem cartItem = cartItemRepository
//                            .findById(cartOrderDto.getCartItemId())
//                            .orElseThrow(EntityNotFoundException::new);
//
//            OrderDto orderDto = new OrderDto();
//            orderDto.setItemId(cartItem.getItem().getId());
//            orderDto.setCount(cartItem.getCount());
//            orderDtoList.add(orderDto);
//        }
//
//        Long orderId = orderService.orders(orderDtoList, email);
//        for (CartOrderDto cartOrderDto : cartOrderDtoList) {
//            CartItem cartItem = cartItemRepository
//                            .findById(cartOrderDto.getCartItemId())
//                            .orElseThrow(EntityNotFoundException::new);
//            cartItemRepository.delete(cartItem);
//        }
//
//        return orderId;
//    }

}
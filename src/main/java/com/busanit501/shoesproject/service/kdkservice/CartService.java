package com.busanit501.shoesproject.service.kdkservice;

import com.busanit501.shoesproject.domain.kdkdomain.Cart;
import com.busanit501.shoesproject.domain.kdkdomain.CartItem;
import com.busanit501.shoesproject.domain.kdkdomain.Item;
import com.busanit501.shoesproject.domain.kdkdomain.Member;
import com.busanit501.shoesproject.dto.kdkdto.*;
import com.busanit501.shoesproject.repository.kdkrepository.CartItemRepository;
import com.busanit501.shoesproject.repository.kdkrepository.CartRepository;
import com.busanit501.shoesproject.repository.kdkrepository.ItemRepository;
import com.busanit501.shoesproject.repository.kdkrepository.MemberRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static com.busanit501.shoesproject.domain.kdkdomain.QItem.item;

@Service
@RequiredArgsConstructor
@Transactional
@Log4j2
public class CartService {

    private final ItemRepository itemRepository;
    private final MemberRepository memberRepository;
    private final CartRepository cartRepository;
    private final CartItemRepository cartItemRepository;
    private final ModelMapper modelMapper;


    public Long addCartItem(CartItemDTO cartItemDto, Long memberId){
        // 아이템 조회
        Item item = itemRepository.findByItemId(cartItemDto.getItemId());

        // 회원 조회
        Member member = memberRepository.findByMemberId(memberId);

        Cart cart = cartRepository.findByMember_MemberId(member.getMemberId());

        if(cart == null){
            cart = Cart.createCart(member);
            cartRepository.save(cart);
        }

        // 카트 아이템 조회 또는 생성
        CartItem savedCartItem = cartItemRepository.findByCart_CartIdAndItem_ItemId(cart.getCartId(), item.getItemId());


        // 기존 카트 아이템이 있으면 수량 증가
        if(savedCartItem != null){
            savedCartItem.addCount(cartItemDto.getCount());
            return savedCartItem.getCartItemId();
        } else {
            CartItem cartItem = CartItem.createCartItem(cart, item, cartItemDto.getCount());
            cartItemRepository.save(cartItem);
            return cartItem.getCartItemId();
        }
    }



//    private Cart createAndSaveCart(Member member) {
//        Cart cart = Cart.createCart(member);
//        cartRepository.save(cart);
//        return cart;
//    }

    @Transactional(readOnly = true)
    public List<CartDetailDTO> getCartList(Long memberId) {
        List<CartDetailDTO> cartDetailDtoList = new ArrayList<>();

        Member member = memberRepository.findByMemberId(memberId);

        Cart cart = cartRepository.findByMember_MemberId(member.getMemberId());

        if(cart == null){
            cart = Cart.createCart(member);
            cartRepository.save(cart);
            return cartDetailDtoList;
        }

        cartDetailDtoList = cartItemRepository.findCartDetailDtoList(cart.getCartId());
        return cartDetailDtoList;
    }

//    @Transactional(readOnly = true)
//    public boolean validateCartItem(Long cartItemId, Long memberId) {
//        Member curMember = memberRepository.findByMemberId(memberId);
//
//
//        CartItem cartItem = cartItemRepository.findById(cartItemId)
//                .orElseThrow(() -> new EntityNotFoundException("장바구니가 비어있습니다."));
//
//        Member savedMember = cartItem.getCart().getMember();
//
//        return curMember.getMemberId().equals(savedMember.getMemberId());
//    }
//
//    public void updateCartItemCount(Long cartItemId, int count) {
//        if (count <= 0) {
//            throw new IllegalArgumentException("수량은 0보다 커야합니다.");
//        }
//
//        CartItem cartItem = cartItemRepository.findById(cartItemId)
//                .orElseThrow(EntityNotFoundException::new);
//
//        cartItem.updateCount(count);
//    }
//
    public void deleteCartItem(Long cartItemId) {
        CartItem cartItem = cartItemRepository.findById(cartItemId)
                .orElseThrow(EntityNotFoundException::new);
        cartItemRepository.delete(cartItem);
    }
}



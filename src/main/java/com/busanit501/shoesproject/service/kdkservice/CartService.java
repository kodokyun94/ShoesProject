package com.busanit501.shoesproject.service.kdkservice;

import com.busanit501.shoesproject.domain.kdkdomain.Cart;
import com.busanit501.shoesproject.domain.kdkdomain.CartItem;
import com.busanit501.shoesproject.domain.kdkdomain.Item;
import com.busanit501.shoesproject.domain.lsjdomain.ShoesMember;
import com.busanit501.shoesproject.domain.nhjdomain.Size;
import com.busanit501.shoesproject.dto.kdkdto.*;
import com.busanit501.shoesproject.repository.kdkrepository.CartItemRepository;
import com.busanit501.shoesproject.repository.kdkrepository.CartRepository;
import com.busanit501.shoesproject.repository.kdkrepository.ItemRepository;
import com.busanit501.shoesproject.repository.kdkrepository.kdkShoesRepository;
import com.busanit501.shoesproject.repository.lsjrepository.lsjShoesRepository;
import com.busanit501.shoesproject.repository.nhjrepository.SizeRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
@Log4j2
public class CartService {

    private final ItemRepository itemRepository;
    private final kdkShoesRepository kdkShoesRepository;
    private final CartRepository cartRepository;
    private final CartItemRepository cartItemRepository;
    private final ModelMapper modelMapper;
    private final SizeRepository sizeRepository;


    public List<Size> getAllSizes() {
        return sizeRepository.findAll();
    }

    public Long addCartItem(CartItemDTO cartItemDto, String memberEmail){
        // 아이템 조회
        Item item = itemRepository.findByItemId(cartItemDto.getItemId());

        // 회원 조회
        ShoesMember shoesMember = kdkShoesRepository.findByMemberEmail(memberEmail);

        Cart cart = cartRepository.findByShoesMember(shoesMember.getMemberEmail());


        if(cart == null){
            cart = Cart.createCart(shoesMember);
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
    public List<CartDetailDTO> getCartList(String memberEmail) {
        List<CartDetailDTO> cartDetailDtoList = new ArrayList<>();

        ShoesMember member = kdkShoesRepository.findByMemberEmail(memberEmail);

        Cart cart = cartRepository.findByShoesMember(member.getMemberEmail());

        Size size = (Size) sizeRepository.findAll();

        if(cart == null){
            cart = Cart.createCart(member);
            cartRepository.save(cart);
            return cartDetailDtoList;
        }

        cartDetailDtoList = cartItemRepository.findCartDetailDtoList(cart.getCartId());
        return cartDetailDtoList;
    }

    public void addSizeToCart(Long itemId, String itemName) {
        Item item = itemRepository.findByItemId(itemId);

        if (item != null) {
            // 아이템 정보 출력
            System.out.println("장바구니에 아이템 추가: " + item.getItemName() + ", " + item.getItemId());

            // 여기에 실제로 장바구니에 아이템을 추가하는 로직을 구현할 수 있습니다.
            // 예를 들어, 세션에 아이템을 추가하거나 데이터베이스에 저장할 수 있습니다.
        } else {
            System.out.println("해당 itemId에 해당하는 아이템을 찾을 수 없습니다: " + itemId);
        }
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



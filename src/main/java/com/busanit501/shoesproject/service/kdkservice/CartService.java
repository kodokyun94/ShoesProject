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
public class CartService {

    private final ItemRepository itemRepository;
    private final MemberRepository memberRepository;
    private final CartRepository cartRepository;
    private final CartItemRepository cartItemRepository;
    private final ModelMapper modelMapper;

    public Long addCart(CartItemDTO cartItemDto, String memberEmail) {


        Item item = itemRepository.findByItemId();

        Member member = memberRepository.findByMemberEmail(memberEmail);

        Cart cart = cartRepository.findByMember_MemberId(member.getMemberId());

        if(cart == null){
            cart = Cart.createCart(member);
            cartRepository.save(cart);
        }

        CartItem savedCartItem = cartItemRepository.findByCart_CartIdAndItem_ItemId(cart.getCartId(), item.getItemId());

        if (savedCartItem != null) {
            savedCartItem.addCount(cartItemDto.getCount());
            return savedCartItem.getCartItemId();
        } else {
            CartItem cartItem = CartItem.createCartItem(cart, item, cartItemDto.getCount());
            cartItemRepository.save(cartItem);
            return cartItem.getCartItemId();
        }
    }

    private Cart createAndSaveCart(Member member) {
        Cart cart = Cart.createCart(member);
        cartRepository.save(cart);
        return cart;
    }

    @Transactional(readOnly = true)
    public List<CartDetailDTO> getCartList(String memberEmail) {
        List<CartDetailDTO> cartDetailDtoList = new ArrayList<>();

        Member member = memberRepository.findByMemberEmail(memberEmail);

        Cart cart = cartRepository.findByMember_MemberId(member.getMemberId());

        if(cart == null){
            cart = Cart.createCart(member);
            cartRepository.save(cart);
            return cartDetailDtoList;
        }

        cartDetailDtoList = cartItemRepository.findCartDetailDtoList(cart.getCartId());
        return cartDetailDtoList;
    }

    @Transactional(readOnly = true)
    public boolean validateCartItem(Long cartItemId, String memberEmail) {
        Member curMember = memberRepository.findByMemberEmail(memberEmail);


        CartItem cartItem = cartItemRepository.findById(cartItemId)
                .orElseThrow(() -> new EntityNotFoundException("장바구니가 비어있습니다."));

        Member savedMember = cartItem.getCart().getMember();

        return curMember.getMemberId().equals(savedMember.getMemberId());
    }

    public void updateCartItemCount(Long cartItemId, int count) {
        if (count <= 0) {
            throw new IllegalArgumentException("수량은 0보다 커야합니다.");
        }

        CartItem cartItem = cartItemRepository.findById(cartItemId)
                .orElseThrow(EntityNotFoundException::new);

        cartItem.updateCount(count);
    }

    public void deleteCartItem(Long cartItemId) {
        CartItem cartItem = cartItemRepository.findById(cartItemId)
                .orElseThrow(EntityNotFoundException::new);
        cartItemRepository.delete(cartItem);
    }
}



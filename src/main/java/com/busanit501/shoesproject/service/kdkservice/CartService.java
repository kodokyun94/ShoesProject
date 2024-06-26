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

    public Long addCart(CartItemDTO cartItemDto, String memberEmail) {
        Item item = itemRepository.findByItemId(cartItemDto.getItemId())
                .orElseThrow(() -> new EntityNotFoundException("Item not found"));

        Member member = memberRepository.findByMemberEmail(memberEmail)
                .orElseThrow(() -> new EntityNotFoundException("Member not found"));

        Cart cart = cartRepository.findByMember_MemberId(member.getMemberId())
                .orElseGet(() -> createAndSaveCart(member));

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
        Member member = memberRepository.findByMemberEmail(memberEmail)
                .orElseThrow(() -> new EntityNotFoundException("Member not found"));

        Cart cart = cartRepository.findByMember_MemberId(member.getMemberId())
                .orElse(null);

        if (cart == null) {
            return new ArrayList<>();
        }

        return cartItemRepository.findCartDetailDtoList(cart.getCartId());
    }

    @Transactional(readOnly = true)
    public boolean validateCartItem(Long cartItemId, String memberEmail) {
        Member curMember = memberRepository.findByMemberEmail(memberEmail)
                .orElseThrow(() -> new EntityNotFoundException("Member not found"));

        CartItem cartItem = cartItemRepository.findById(cartItemId)
                .orElseThrow(() -> new EntityNotFoundException("CartItem not found"));

        Member savedMember = cartItem.getCart().getMember();

        return curMember.getMemberId().equals(savedMember.getMemberId());
    }

    public void updateCartItemCount(Long cartItemId, int count) {
        if (count <= 0) {
            throw new IllegalArgumentException("Count must be greater than zero");
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



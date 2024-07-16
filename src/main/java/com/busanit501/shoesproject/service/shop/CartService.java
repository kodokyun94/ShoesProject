package com.busanit501.shoesproject.service.shop;

import com.busanit501.shoesproject.domain.Cart;
import com.busanit501.shoesproject.domain.CartItem;
import com.busanit501.shoesproject.domain.Item;
import com.busanit501.shoesproject.domain.Member;
import com.busanit501.shoesproject.dto.shop.CartDetailDTO;
import com.busanit501.shoesproject.dto.shop.CartItemDTO;
import com.busanit501.shoesproject.dto.shop.CartOrderDTO;
import com.busanit501.shoesproject.dto.shop.OrderDTO;
import com.busanit501.shoesproject.repository.shop.CartItemRepository;
import com.busanit501.shoesproject.repository.shop.CartRepository;
import com.busanit501.shoesproject.repository.shop.ItemRepository;
import com.busanit501.shoesproject.repository.board.MemberRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.thymeleaf.util.StringUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
@Log4j2
public class CartService {

    private final ItemRepository itemRepository;
    // 합치기 수정
    private final MemberRepository memberRepository;
    private final CartRepository cartRepository;
    private final CartItemRepository cartItemRepository;
    private final OrderService orderService;

    public Long addCart(CartItemDTO cartItemDto, String mid) {
        log.info("CartService cartItemDto 확인 1: " + cartItemDto);
        Item item = itemRepository.findById(cartItemDto.getItemId())
                .orElseThrow(EntityNotFoundException::new);
        log.info("CartService cartItemDto 확인 2: " + item);
        // 합치기 수정
        Optional<Member> result = memberRepository.findByMid(mid);
        Member member = result.orElseThrow();
        log.info("CartService member 확인 3: " + member);
        // 합치기 수정
        Cart cart = cartRepository.findByMemberMid(member.getMid());
        log.info("CartService cart 확인 4: " + cart);
        if (cart == null) {
            log.info("CartService cart 확인 5: " + cart);
            cart = Cart.createCart(member);
            log.info("CartService cart 확인 6: " + cart);
            cartRepository.save(cart);
        }


        CartItem savedCartItem = cartItemRepository.findByCartIdAndItemId(cart.getId(), item.getId());
        log.info("CartService savedCartItem 확인 7: " + savedCartItem);

        if (savedCartItem != null) {
            savedCartItem.addCount(cartItemDto.getCount());
            return savedCartItem.getId();
        } else {
            CartItem cartItem = CartItem.createCartItem(cart, item, cartItemDto.getCount());
            cartItemRepository.save(cartItem);
            return cartItem.getId();
        }
    }

    @Transactional(readOnly = true)
    public List<CartDetailDTO> getCartList(String mid) {

        List<CartDetailDTO> cartDetailDtoList = new ArrayList<>();

        // 합치기 수정
        //ShopMember shopMember = memberRepository.findByEmail(email);
        Optional<Member> result = memberRepository.findByMid(mid);
        Member member = result.orElseThrow();
        Cart cart = cartRepository.findByMemberMid(member.getMid());
        if (cart == null) {
            return cartDetailDtoList;
        }

        cartDetailDtoList = cartItemRepository.findCartDetailDtoList(cart.getId());
        return cartDetailDtoList;
    }

    @Transactional(readOnly = true)
    public boolean validateCartItem(Long cartItemId, String mid) {
        // 합치기 수정

//        ShopMember curShopMember = memberRepository.findByEmail(email);
        Optional<Member> result = memberRepository.findByMid(mid);
        Member curMember = result.orElseThrow();
        CartItem cartItem = cartItemRepository.findById(cartItemId)
                .orElseThrow(EntityNotFoundException::new);
        Member savedMember = cartItem.getCart().getMember();

        if (!StringUtils.equals(curMember.getEmail(), savedMember.getEmail())) {
            return false;
        }

        return true;
    }

    public void updateCartItemCount(Long cartItemId, int count) {
        CartItem cartItem = cartItemRepository.findById(cartItemId)
                .orElseThrow(EntityNotFoundException::new);

        cartItem.updateCount(count);
    }

    public void deleteCartItem(Long cartItemId) {
        CartItem cartItem = cartItemRepository.findById(cartItemId)
                .orElseThrow(EntityNotFoundException::new);
        cartItemRepository.delete(cartItem);
    }

    public Long orderCartItem(List<CartOrderDTO> cartOrderDtoList, String mid) {
        List<OrderDTO> orderDtoList = new ArrayList<>();

        for (CartOrderDTO cartOrderDto : cartOrderDtoList) {
            CartItem cartItem = cartItemRepository
                    .findById(cartOrderDto.getCartItemId())
                    .orElseThrow(EntityNotFoundException::new);

            OrderDTO orderDto = new OrderDTO();
            orderDto.setItemId(cartItem.getItem().getId());
            orderDto.setCount(cartItem.getCount());
            orderDtoList.add(orderDto);
        }

        Long orderId = orderService.orders(orderDtoList, mid);
        for (CartOrderDTO cartOrderDto : cartOrderDtoList) {
            CartItem cartItem = cartItemRepository
                    .findById(cartOrderDto.getCartItemId())
                    .orElseThrow(EntityNotFoundException::new);
            cartItemRepository.delete(cartItem);
        }

        return orderId;
    }

}
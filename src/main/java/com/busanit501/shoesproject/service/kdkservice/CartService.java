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
import com.busanit501.shoesproject.repository.lsjrepository.lsjShoesRepository;
import com.busanit501.shoesproject.repository.nhjrepository.SizeRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.modelmapper.ModelMapper;
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
    private final lsjShoesRepository memberRepository;
    private final CartRepository cartRepository;
    private final CartItemRepository cartItemRepository;
    private final OrderService orderService;

    public Long addCart(CartItemDto cartItemDto, String memberId) {
        log.info("CartService cartItemDto 확인 1: " + cartItemDto);
        Item item = itemRepository.findById(cartItemDto.getItemId())
                .orElseThrow(EntityNotFoundException::new);
        log.info("CartService cartItemDto 확인 2: " + item);
        // 합치기 수정
        Optional<ShoesMember> result = memberRepository.findByMemberId(memberId);
        ShoesMember member = result.orElseThrow();
        log.info("CartService member 확인 3: " + member);
        // 합치기 수정
        Cart cart = cartRepository.findByShoesMemberMemberId(member.getMemberId());
        log.info("CartService cart 확인 4: " + cart);
        if (cart == null) {
            log.info("CartService cart 확인 5: " + cart);
            cart = Cart.createCart(member);
            log.info("CartService cart 확인 6: " + cart);
            cartRepository.save(cart);
        }


        CartItem savedCartItem = cartItemRepository.findByCartIdAndItemId(cart.getCartId(), item.getItemId());
        log.info("CartService savedCartItem 확인 7: " + savedCartItem);

        if (savedCartItem != null) {
            savedCartItem.addCount(cartItemDto.getCount());
            return savedCartItem.getCartItemId();
        } else {
            CartItem cartItem = CartItem.createCartItem(cart, item, cartItemDto.getCount());
            cartItemRepository.save(cartItem);
            return cartItem.getCartItemId();
        }
    }

    @Transactional(readOnly = true)
    public List<CartDetailDto> getCartList(String memberId) {

        List<CartDetailDto> cartDetailDtoList = new ArrayList<>();

        // 합치기 수정
        //ShopMember shopMember = memberRepository.findByEmail(email);
        Optional<ShoesMember> result = memberRepository.findByMemberId(memberId);
        ShoesMember member = result.orElseThrow();
        Cart cart = cartRepository.findByShoesMemberMemberId(member.getMemberId());
        if (cart == null) {
            return cartDetailDtoList;
        }

        cartDetailDtoList = cartItemRepository.findCartDetailDtoList(cart.getCartId());
        return cartDetailDtoList;
    }

    @Transactional(readOnly = true)
    public boolean validateCartItem(Long cartItemId, String memberId) {
        // 합치기 수정

//        ShopMember curShopMember = memberRepository.findByEmail(email);
        Optional<ShoesMember> result = memberRepository.findByMemberId(memberId);
        ShoesMember curMember = result.orElseThrow();
        CartItem cartItem = cartItemRepository.findById(cartItemId)
                .orElseThrow(EntityNotFoundException::new);
        ShoesMember savedMember = cartItem.getCart().getShoesMember();

        if (!StringUtils.equals(curMember.getMemberEmail(), savedMember.getMemberEmail())) {
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

    public Long orderCartItem(List<CartOrderDto> cartOrderDtoList, String memberId) {
        List<OrderDto> orderDtoList = new ArrayList<>();

        for (CartOrderDto cartOrderDto : cartOrderDtoList) {
            CartItem cartItem = cartItemRepository
                    .findById(cartOrderDto.getCartItemId())
                    .orElseThrow(EntityNotFoundException::new);

            OrderDto orderDto = new OrderDto();
            orderDto.setItemId(cartItem.getItem().getItemId());
            orderDto.setCount(cartItem.getCount());
            orderDtoList.add(orderDto);
        }

        Long orderId = orderService.orders(orderDtoList, memberId);
        for (CartOrderDto cartOrderDto : cartOrderDtoList) {
            CartItem cartItem = cartItemRepository
                    .findById(cartOrderDto.getCartItemId())
                    .orElseThrow(EntityNotFoundException::new);
            cartItemRepository.delete(cartItem);
        }

        return orderId;
    }
}



package com.busanit501.shoesproject.service.kdkservice;

import com.busanit501.shoesproject.domain.kdkdomain.Cart;
import com.busanit501.shoesproject.domain.kdkdomain.Item;
import com.busanit501.shoesproject.domain.kdkdomain.Member;
import com.busanit501.shoesproject.dto.kdkdto.CartDTO;
import com.busanit501.shoesproject.repository.kdkrepository.CartRepository;
import com.busanit501.shoesproject.repository.kdkrepository.ItemRepository;
import com.busanit501.shoesproject.repository.kdkrepository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CartServiceImpl implements CartService {

    private final CartRepository cartRepository;
    private final MemberRepository memberRepository;
    private final ItemRepository itemRepository;

    @Autowired
    public CartServiceImpl(CartRepository cartRepository, MemberRepository memberRepository, ItemRepository itemRepository) {
        this.cartRepository = cartRepository;
        this.memberRepository = memberRepository;
        this.itemRepository = itemRepository;
    }

    @Override
    public CartDTO saveCart(CartDTO cartDTO) {
        Optional<Member> memberOptional = memberRepository.findById(cartDTO.getMember_Id());
        if (!memberOptional.isPresent()) {
            throw new RuntimeException("Member not found");
        }

        List<Item> items = cartDTO.getItem_Id().stream()
                .map(itemId -> itemRepository.findById(itemId)
                        .orElseThrow(() -> new RuntimeException("Item not found")))
                .collect(Collectors.toList());

        Cart cart = new Cart();
        cart.setMember(memberOptional.get());
        cart.setItem(items);

        Cart savedCart = cartRepository.save(cart);

        cartDTO.setCart_Id(savedCart.getCart_id());
        return cartDTO;
    }

    @Override
    public CartDTO getCartById(Long id) {
        Optional<Cart> optionalCart = cartRepository.findById(id);
        if (!optionalCart.isPresent()) {
            throw new RuntimeException("Cart not found");
        }

        Cart cart = optionalCart.get();
        CartDTO cartDTO = new CartDTO();
        cartDTO.setCart_Id(cart.getCart_id());
        cartDTO.setMember_Id(cart.getMember().getMember_id());
        cartDTO.setItem_Id(cart.getItem().stream().map(Item::getItem_id).collect(Collectors.toList()));

        return cartDTO;
    }

    @Override
    public List<CartDTO> getAllCarts() {
        return cartRepository.findAll().stream().map(cart -> {
            CartDTO cartDTO = new CartDTO();
            cartDTO.setCart_Id(cart.getCart_id());
            cartDTO.setMember_Id(cart.getMember().getMember_id());
            cartDTO.setItem_Id(cart.getItem().stream().map(Item::getItem_id).collect(Collectors.toList()));
            return cartDTO;
        }).collect(Collectors.toList());
    }

    @Override
    public void deleteCart(Long id) {
        cartRepository.deleteById(id);
    }
}

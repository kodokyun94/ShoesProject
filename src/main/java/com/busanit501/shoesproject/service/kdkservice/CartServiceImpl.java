package com.busanit501.shoesproject.service.kdkservice;

import com.busanit501.shoesproject.dto.kdkdto.PageRequestDTO;
import com.busanit501.shoesproject.domain.kdkdomain.Cart;
import com.busanit501.shoesproject.domain.kdkdomain.Item;
import com.busanit501.shoesproject.dto.kdkdto.CartDTO;
import com.busanit501.shoesproject.dto.kdkdto.ItemDTO;
import com.busanit501.shoesproject.repository.kdkrepository.CartRepository;
import com.busanit501.shoesproject.repository.kdkrepository.ItemRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Log4j2
public class CartServiceImpl implements CartService {

    private final CartRepository cartRepository;
//    private final MemberRepository memberRepository;
    private final ItemRepository itemRepository;
    private final ModelMapper modelMapper;


    @Override
    @Transactional
    public Long register(CartDTO cartDTO) {
        Cart cart = modelMapper.map(cartDTO, Cart.class);
        Item item = itemRepository.findById(cartDTO.getCart_Id())
                .orElseThrow(() -> new RuntimeException("Item not found"));
        item.setCart(cart);
        cartRepository.save(cart);
        return cart.getCart_id();
    }


    @Override
    public CartDTO read(Long cart_id) {
        Cart cart = cartRepository.findById(cart_id)
                .orElseThrow(() -> new RuntimeException("Cart not found"));

        List<ItemDTO> itemDTOList = cart.getItems().stream()
                .map(item -> modelMapper.map(item, ItemDTO.class))
                .collect(Collectors.toList());

        CartDTO cartDTO = modelMapper.map(cart, CartDTO.class);
        cartDTO.setItems(itemDTOList);
        return cartDTO;
    }


    @Override
    @Transactional
    public void delete(Long cart_id) {
        Cart cart = cartRepository.findById(cart_id)
                .orElseThrow(() -> new RuntimeException("Cart not found"));

        // Delete the cart
        cartRepository.delete(cart);
    }


    @Override
    public CartDTO getCartItems(Long cart_id, PageRequestDTO pageRequestDTO) {
        Cart cart = cartRepository.findById(cart_id)
                .orElseThrow(() -> new RuntimeException("Cart not found"));

        List<ItemDTO> itemDTOList = cart.getItems().stream()
                .map(item -> modelMapper.map(item, ItemDTO.class))
                .collect(Collectors.toList());

        CartDTO cartDTO = modelMapper.map(cart, CartDTO.class);
        cartDTO.setItems(itemDTOList);
        return cartDTO;
    }



    @Override
    @Transactional
    public void update(CartDTO cartDTO) {
        Cart cart = cartRepository.findById(cartDTO.getCart_Id())
                .orElseThrow(() -> new RuntimeException("Cart not found"));

        // Update cart information if needed

        // Save the updated cart
        cartRepository.save(cart);
    }
}

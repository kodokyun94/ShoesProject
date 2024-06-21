package com.busanit501.shoesproject.controller.kdkcontroller;

import com.busanit501.shoesproject.dto.kdkdto.CartDTO;
import com.busanit501.shoesproject.service.kdkservice.CartService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.ui.Model;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Collections;
import java.util.List;

@Controller
@RequestMapping("/cart")
@Log4j2
@RequiredArgsConstructor
@Tag(name = "CartController", description = "장바구니 관련 API")
public class CartController {
    private final CartService cartService;



    @GetMapping("/list")
    public String getCartList(Model model) {
        // 여기에 실제 데이터를 가져오는 로직을 추가
        List<CartDTO> cartItems = Collections.singletonList(cartService.getCartItems()); // 서비스에서 장바구니 아이템을 가져옴

        model.addAttribute("cartItems", cartItems);
        return "cartList";
    }


}//class End

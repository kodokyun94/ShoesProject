package com.busanit501.shoesproject.controller.kdkcontroller;

//@Controller
//@RequestMapping("/cart")
//@Log4j2
//@RequiredArgsConstructor
//@Tag(name = "CartController", description = "장바구니 관련 API")
//public class CartController {
//    @Autowired
//    private CartService cartService;
//
//    @GetMapping("/cart")
//    public String showCart(Model model) {
//        List<CartItemDTO> cartItems = cartService.getCartItems(1L); // 예시로 1번 카트를 가져옵니다. 실제 구현에서는 사용자 ID나 세션 ID로 가져와야 합니다.
//        model.addAttribute("cartItems", cartItems);
//        return "cart";
//    }
//
//
//}//class End

import com.busanit501.shoesproject.dto.kdkdto.CartItemDTO;
import com.busanit501.shoesproject.service.kdkservice.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class CartController {

    @Autowired
    private CartService cartService;

    @GetMapping("/cart")
    public String showCart(Model model) {
        List<CartItemDTO> cartItems = cartService.getCartItems(1L); // 예시로 1번 카트를 가져옵니다. 실제 구현에서는 사용자 ID나 세션 ID로 가져와야 합니다.
        model.addAttribute("cartItems", cartItems);
        return "cart";
    }

    @PostMapping("/cart/delete")
    public String deleteCartItem(@RequestParam("id") Long id) {
        cartService.deleteCartItem(id);
        return "redirect:/cart";
    }
}

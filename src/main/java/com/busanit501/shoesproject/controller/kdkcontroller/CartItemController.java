package com.busanit501.shoesproject.controller.kdkcontroller;


import com.busanit501.shoesproject.domain.kdkdomain.CartItem;
import com.busanit501.shoesproject.domain.kdkdomain.Item;
import com.busanit501.shoesproject.domain.kdkdomain.Member;
import com.busanit501.shoesproject.service.kdkservice.CartItemService;
import com.busanit501.shoesproject.service.kdkservice.ItemService;
import com.busanit501.shoesproject.service.kdkservice.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@Controller
@RequestMapping("/cart")
public class CartItemController {

    @Autowired
    private CartItemService cartItemService;

    @Autowired
    private ItemService itemService;

    @Autowired
    private MemberService memberService;

    // 장바구니 화면 표시
    @GetMapping("/")
    public String viewCart(Model model) {
        List<CartItem> cartItems = cartItemService.getAllCartItems();
        model.addAttribute("cartItems", cartItems);
        return "cart";
    }

    // 장바구니에 상품 추가 폼 표시
    @GetMapping("/add")
    public String showAddForm(Model model) {
        model.addAttribute("cartItem", new CartItem());
        model.addAttribute("items", itemService.getAllItems());
        model.addAttribute("members", memberService.getAllMembers());
        return "addCartItem";
    }

    // 장바구니에 상품 추가 처리
    @PostMapping("/add")
    public String addToCart(@ModelAttribute("cartItem") CartItem cartItem,
                            @RequestParam("itemId") Long itemId,
                            @RequestParam("memberId") Long memberId) {
        Item item = itemService.getItemById(itemId)
                .orElseThrow(() -> new IllegalArgumentException("Invalid item Id:" + itemId));
        Member member = memberService.getMemberById(memberId)
                .orElseThrow(() -> new IllegalArgumentException("Invalid member Id:" + memberId));
        cartItem.setItem(item);
        cartItem.setMember(member);
        cartItemService.saveCartItem(cartItem);
        return "redirect:/cart/";
    }

    // 장바구니에서 상품 삭제 처리
    @DeleteMapping("/delete/{id}")
    public String deleteFromCart(@PathVariable("id") Long id) {
        cartItemService.deleteCartItem(id);
        return "redirect:/cart/";
    }
}

package com.busanit501.shoesproject.controller.kdkcontroller;

import com.busanit501.shoesproject.domain.lsjdomain.ShoesMember;
import com.busanit501.shoesproject.dto.kdkdto.CartDetailDto;
import com.busanit501.shoesproject.service.kdkservice.CartService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequestMapping("/shoes")
@Log4j2
@RequiredArgsConstructor
public class CartController {

    private final CartService cartService;
    private final kdkShoesRepository kdkShoesRepository; // 회원 조회를 위한 MemberRepository

    @GetMapping("/cart")
    public String showCart(Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String memberEmail = null;

        if (authentication != null && authentication.getPrincipal() instanceof UserDetails) {
            UserDetails userDetails = (UserDetails) authentication.getPrincipal();
            memberEmail = userDetails.getUsername();
        }

        if (memberEmail != null) {
            ShoesMember shoesMember = kdkShoesRepository.findByMemberEmail(memberEmail);

            if (shoesMember != null) {
                String memberId = shoesMember.getMemberId();
                List<CartDetailDto> cartItems = cartService.getCartList(memberId);
                log.info("cartItems showCart : " + cartItems);
                model.addAttribute("cartItems", cartItems);
            } else {
                String warningMessage = "Member not found for email: " + memberEmail;
                log.warn(warningMessage);
                model.addAttribute("warningMessage", warningMessage);
            }

        } else {
            String warningMessage = "No authenticated user found";
            log.warn(warningMessage);
            model.addAttribute("warningMessage", warningMessage);
        }

        return "shoes/cart"; // 뷰 이름을 반환
    }


    @PostMapping("/cart/delete/{cartItemId}")
    public String deleteCartItem(@PathVariable Long cartItemId) {
        cartService.deleteCartItem(cartItemId);
        return "redirect:/shoes/cart"; // 삭제 후 장바구니 페이지로
    }


    @PostMapping("/addToCart")
    public String addToCart(@RequestParam Long itemId, @RequestParam String itemName, RedirectAttributes redirectAttributes) {
        // 여기서 itemId와 itemName을 사용하여 장바구니에 아이템을 추가하는 로직을 구현합니다.
        cartService.addSizeToCart(itemId, itemName);

        // 추가가 완료되면, 다시 장바구니 화면으로 리다이렉트하거나 메시지를 추가할 수 있습니다.
        redirectAttributes.addFlashAttribute("message", "아이템이 장바구니에 추가되었습니다.");

        return "redirect:/shoes/cart"; // 장바구니 화면으로 리다이렉트
    }

//    @PostMapping(value = "/cart")
//    public @ResponseBody ResponseEntity order(@RequestBody @Valid CartItemDTO cartItemDTO, BindingResult bindingResult, Principal principal){
//        if(bindingResult.hasErrors()){
//            StringBuilder sb = new StringBuilder();
//            List<FieldError> fieldErrors = bindingResult.getFieldErrors();
//            for (FieldError fieldError : fieldErrors) {
//                sb.append(fieldError.getDefaultMessage());
//            }
//            return new ResponseEntity<String>(sb.toString(), HttpStatus.BAD_REQUEST);
//        }
//        String email = principal.getName();
//        Long Id;
//        try {
//            Id = cartService.addCart(cartItemDTO, email);
//        } catch(Exception e){
//            return new ResponseEntity<String>(e.getMessage(), HttpStatus.BAD_REQUEST);
//        }
//        return new ResponseEntity<Long>(Id, HttpStatus.OK);
//    }


//    @GetMapping("/cart")
//    public String showCartItems(Model model) {
//        MemberDTO memberDTO = m
//        List<CartDetailDTO> cartItems = cartService.getCartList();
//        model.addAttribute("cartItems", cartItems);
//        return "/shoes/cart"; // 뷰 이름
//    }
//
//
//    @PatchMapping(value = "/cartItem/{cartId}")
//    public @ResponseBody ResponseEntity updateCartItem(@PathVariable("cartId") Long cartId, int count, Principal principal){
//        if(count <= 0){
//            return new ResponseEntity<String>("최소 1개 이상 담아주세요", HttpStatus.BAD_REQUEST);
//        } else if(!cartService.validateCartItem(cartId, principal.getName())){
//            return new ResponseEntity<String>("수정 권한이 없습니다.", HttpStatus.FORBIDDEN);
//        }
//        cartService.updateCartItemCount(cartId, count);
//        return new ResponseEntity<Long>(cartId, HttpStatus.OK);
//    }
//
//
//    @DeleteMapping(value = "/cartItem/{cartId}")
//    public @ResponseBody ResponseEntity deleteCartItem(@PathVariable("cartId") Long cartId, Principal principal){
//        if(!cartService.validateCartItem(cartId, principal.getName())){
//            return new ResponseEntity<String>("수정 권한이 없습니다.", HttpStatus.FORBIDDEN);
//        }
//        cartService.deleteCartItem(cartId);
//        return new ResponseEntity<Long>(cartId, HttpStatus.OK);
//    }

}

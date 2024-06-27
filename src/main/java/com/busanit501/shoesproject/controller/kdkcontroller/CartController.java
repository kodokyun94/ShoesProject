package com.busanit501.shoesproject.controller.kdkcontroller;

import com.busanit501.shoesproject.domain.lsjdomain.ShoesMember;
import com.busanit501.shoesproject.dto.kdkdto.CartDetailDTO;
import com.busanit501.shoesproject.repository.kdkrepository.kdkShoesRepository;
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
                List<CartDetailDTO> cartItems = cartService.getCartList(memberId);
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


    @PostMapping("/add-to-cart")
    public String addToCart(@RequestParam Long itemId, @RequestParam String size, Model model) {
        // 고객 식별 정보 (예: 회원 이메일) 가져오기
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String memberEmail = null;

        if (authentication != null && authentication.getPrincipal() instanceof UserDetails) {
            UserDetails userDetails = (UserDetails) authentication.getPrincipal();
            memberEmail = userDetails.getUsername();
        }

        if (memberEmail != null) {
            // 고객 정보 조회 (예: ShoesMember가 어떻게 구현되었는지에 따라 다름)
            ShoesMember shoesMember = kdkShoesRepository.findByMemberEmail(memberEmail);

            if (shoesMember != null) {
                // 아이템 ID와 사이즈를 사용하여 장바구니에 추가
                cartService.addToCart(shoesMember.getMemberId(), itemId, size);

                // 장바구니 페이지로 리다이렉트
                return "redirect:/shoes/cart";
            } else {
                // 고객 정보를 찾을 수 없는 경우 처리
                String warningMessage = "Member not found for email: " + memberEmail;
                log.warn(warningMessage);
                model.addAttribute("warningMessage", warningMessage);
                return "error-page"; // 예시에서는 에러 페이지로 리다이렉트
            }
        } else {
            // 인증된 사용자가 없는 경우 처리
            String warningMessage = "No authenticated user found";
            log.warn(warningMessage);
            model.addAttribute("warningMessage", warningMessage);
            return "error-page"; // 예시에서는 에러 페이지로 리다이렉트
        }
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

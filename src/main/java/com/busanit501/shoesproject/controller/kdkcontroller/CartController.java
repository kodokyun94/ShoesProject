package com.busanit501.shoesproject.controller.kdkcontroller;

import com.busanit501.shoesproject.dto.kdkdto.CartDetailDTO;
import com.busanit501.shoesproject.dto.kdkdto.CartItemDTO;
import com.busanit501.shoesproject.service.kdkservice.CartService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@Controller
@RequestMapping("/shoes")
@Log4j2
@RequiredArgsConstructor
public class CartController {

    private final CartService cartService;

//    @PostMapping(value = "/cart")
//    public @ResponseBody ResponseEntity order(@RequestBody @Valid CartItemDTO cartItemDTO, BindingResult bindingResult, Principal principal){
//
//        if(bindingResult.hasErrors()){
//            StringBuilder sb = new StringBuilder();
//            List<FieldError> fieldErrors = bindingResult.getFieldErrors();
//
//            for (FieldError fieldError : fieldErrors) {
//                sb.append(fieldError.getDefaultMessage());
//            }
//
//            return new ResponseEntity<String>(sb.toString(), HttpStatus.BAD_REQUEST);
//        }
//
//        String email = principal.getName();
//        Long Id;
//
//        try {
//            Id = cartService.addCart(cartItemDTO, email);
//        } catch(Exception e){
//            return new ResponseEntity<String>(e.getMessage(), HttpStatus.BAD_REQUEST);
//        }
//
//        return new ResponseEntity<Long>(Id, HttpStatus.OK);
//    }

    @GetMapping(value = "/cart")
    public void orderHist(Principal principal, Model model){
//        List<CartDetailDTO> cartDetailList = cartService.getCartList(principal.getName());
//        model.addAttribute("cartItems", cartDetailList);
//        return "/shoes/cart";
    }

    @PatchMapping(value = "/cartItem/{cartId}")
    public @ResponseBody ResponseEntity updateCartItem(@PathVariable("cartId") Long cartId, int count, Principal principal){

        if(count <= 0){
            return new ResponseEntity<String>("최소 1개 이상 담아주세요", HttpStatus.BAD_REQUEST);
        } else if(!cartService.validateCartItem(cartId, principal.getName())){
            return new ResponseEntity<String>("수정 권한이 없습니다.", HttpStatus.FORBIDDEN);
        }

        cartService.updateCartItemCount(cartId, count);
        return new ResponseEntity<Long>(cartId, HttpStatus.OK);
    }

    @DeleteMapping(value = "/cartItem/{cartId}")
    public @ResponseBody ResponseEntity deleteCartItem(@PathVariable("cartId") Long cartId, Principal principal){

        if(!cartService.validateCartItem(cartId, principal.getName())){
            return new ResponseEntity<String>("수정 권한이 없습니다.", HttpStatus.FORBIDDEN);
        }

        cartService.deleteCartItem(cartId);

        return new ResponseEntity<Long>(cartId, HttpStatus.OK);
    }
}

package com.busanit501.shoesproject.controller.nhjcontroller;

import com.busanit501.shoesproject.domain.nhjdomain.Review;
import com.busanit501.shoesproject.domain.nhjdomain.Size;
import com.busanit501.shoesproject.dto.nhjdto.ReviewDTO;
import com.busanit501.shoesproject.service.kdkservice.CartService;
import com.busanit501.shoesproject.service.nhjservice.ReviewService;
import com.busanit501.shoesproject.service.nhjservice.SizeService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Log4j2
@Controller
@RequestMapping("/shoes")
@RequiredArgsConstructor
public class nhjController {

    private final SizeService sizeService;
    private final ReviewService reviewService;
    private final CartService cartService;

    @GetMapping("/main")
    public void mainGet() {

    }

    @GetMapping("/product")
    public void productGet() {


    }

    @GetMapping("/productpage")
    public void productpageGet(Model model) {
        List<Size> sizes = sizeService.getAllSizes();
        model.addAttribute("sizes", sizes);
        List<Review> reviews = reviewService.getAllReviews();
        model.addAttribute("reviews", reviews);
    }

//    @GetMapping("/cart")
//    public void cartGet(Model model) {
//    }


//    public void cartGet() {
//
//    }

    @PostMapping("/review")
    public String addreview(ReviewDTO reviewDTO) {
        reviewService.saveReview(reviewDTO);
        return "redirect:/shoes/productpage";
    }

}

package com.busanit501.shoesproject.controller.msjcontroller;

import com.busanit501.shoesproject.dto.mjsdto.PageRequestDTO;
import com.busanit501.shoesproject.dto.mjsdto.PageResponseDTO;
import com.busanit501.shoesproject.dto.mjsdto.ShoesListAllDTO;
import com.busanit501.shoesproject.service.mjsservice.ShoesService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/shoes")
@Log4j2
@RequiredArgsConstructor
public class ShoesController {

    @Value("${com.busanit501.upload.path}")
    private String uploadPath;

    private final ShoesService shoesService;

    @GetMapping("/list")
    public void list( PageRequestDTO pageRequestDTO, Model model) {

        log.info("BoardController : /board/list  확인 중, pageRequestDTO : " + pageRequestDTO);

        // dto 변경하기, 메서드도 변경하기. 댓글 갯수 포함, 첨부 이미지들 모두 포함.
        PageResponseDTO<ShoesListAllDTO> responseDTO
                = shoesService.listWithAll(pageRequestDTO);

        // 로그인 유저의 , 정보 가져오기.
//        boolean loginCheck = false;
//        memberService.
//        if ()

        // 서버로부터 응답확인.
        log.info("BoardController 확인 중, responseDTO : " + responseDTO);

        // 서버 -> 화면 데이터 전달.
        model.addAttribute("responseDTO", responseDTO);
        // 로그인 여부에 따라, 로그 아웃 표시하기.


    } //list 닫는 부분

}

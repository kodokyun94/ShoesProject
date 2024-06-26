package com.busanit501.shoesproject.controller.msjcontroller;

import com.busanit501.shoesproject.domain.nhjdomain.Review;
import com.busanit501.shoesproject.domain.nhjdomain.Size;
import com.busanit501.shoesproject.dto.mjsdto.PageRequestDTO;
import com.busanit501.shoesproject.dto.mjsdto.PageResponseDTO;
import com.busanit501.shoesproject.dto.mjsdto.ShoesDTO;
import com.busanit501.shoesproject.dto.mjsdto.ShoesListAllDTO;
import com.busanit501.shoesproject.service.mjsservice.ShoesService;
import com.busanit501.shoesproject.service.nhjservice.ReviewService;
import com.busanit501.shoesproject.service.nhjservice.SizeService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.File;
import java.nio.file.Files;
import java.util.List;

@Controller
@RequestMapping("/shoes")
@Log4j2
@RequiredArgsConstructor
public class ShoesController {

    @Value("${com.busanit501.upload.path}")
    private String uploadPath;

    private final ShoesService shoesService;
    private final SizeService sizeService;
    private final ReviewService reviewService;

    @GetMapping("/product2")
    public void search(UserDetails user, PageRequestDTO pageRequestDTO, Model model) {

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

    @GetMapping("/productpage2")
    public void search2( Long itemId, ShoesDTO shoesDTO2,UserDetails user, PageRequestDTO pageRequestDTO, Model model) {

        ShoesDTO shoesDTO = shoesService.read(itemId);
        model.addAttribute("shoesDTO", shoesDTO);
        log.info("BoardController : /board/list  확인 중, pageRequestDTO : " + pageRequestDTO);

        // dto 변경하기, 메서드도 변경하기. 댓글 갯수 포함, 첨부 이미지들 모두 포함.
        PageResponseDTO<ShoesListAllDTO> responseDTO
                = shoesService.listWithAll(pageRequestDTO);

        // 로그인 유저의 , 정보 가져오기.
//        boolean loginCheck = false;
//        memberService.
//        if ()

        List<Size> sizes = sizeService.getAllSizes();
        model.addAttribute("sizes", sizes);
        List<Review> reviews = reviewService.getAllReviews();
        model.addAttribute("reviews", reviews);

        // 서버로부터 응답확인.
        log.info("BoardController 확인 중, responseDTO : " + responseDTO);

        // 서버 -> 화면 데이터 전달.
        model.addAttribute("responseDTO", responseDTO);
        // 로그인 여부에 따라, 로그 아웃 표시하기.
        model.addAttribute("user", user);

    } //list 닫는 부분
    @GetMapping("/search")
    public void search3( UserDetails user, PageRequestDTO pageRequestDTO, Model model) {

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
        model.addAttribute("user", user);

    } //list 닫는 부분

    @GetMapping("/register")
    public void registerForm( UserDetails user, Model model) {
        // 로그인 여부에 따라, 로그 아웃 표시하기.
        model.addAttribute("user", user);
    }

    @PostMapping("/register")
    public String register(@Valid ShoesDTO shoesDTO
            , BindingResult bindingResult
            , RedirectAttributes redirectAttributes
            , Model model) {
        // 입력중 유효성 체크에 해당 될 때
        if (bindingResult.hasErrors()) {
            log.info("register 중 오류 발생.");
            redirectAttributes.addFlashAttribute(
                    "errors", bindingResult.getAllErrors());
            return "redirect:/shoes/register";
        }
        log.info("화면에서 입력 받은 내용 확인 : " + shoesDTO);

        //화면 -> 서버 -> 서비스 -> 레포지토리 -> 디비, 입력후, 게시글 번호 가져오기
        //화면 <- 서버 <- 서비스 <- 레포지토리 <- 디비
        Long itemId = shoesService.register(shoesDTO);

        // 글쓰기 후, 작성된 게시글 번호 -> 화면 , 임시로 전달.(1회용)
        redirectAttributes.addFlashAttribute("result", itemId);
        redirectAttributes.addFlashAttribute("resultType", "register");
        return "redirect:/shoes/search";

    }

    @GetMapping({"/read", "/update"})
    public void read( Long itemId, ShoesDTO shoesDTO2, UserDetails user, PageRequestDTO pageRequestDTO, Model model) {


        log.info("BoardController : /shoes/read  확인 중, pageRequestDTO : " + pageRequestDTO);

        // 디비에서, bno 번호, 하나의 게시글 디비 정보 가져오기.
        ShoesDTO shoesDTO = shoesService.read(itemId);
        // 서버로부터 응답확인.
        log.info("BoardController 확인 중, shoesDTO : " + shoesDTO);
        // 서버 -> 화면 데이터 전달.
        model.addAttribute("shoesDTO", shoesDTO);
        model.addAttribute("user", user);


    } //read 닫는 부분

    @PostMapping("/update")
    public String update(@Valid ShoesDTO shoesDTO
            , BindingResult bindingResult
            , RedirectAttributes redirectAttributes
            , Model model
            , PageRequestDTO pageRequestDTO) {
        // 입력중 유효성 체크에 해당 될 때
        if (bindingResult.hasErrors()) {
            log.info("update 중 오류 발생.");
            redirectAttributes.addFlashAttribute(
                    "errors", bindingResult.getAllErrors());
            // 서버 -> 화면으로 쿼리 스트링으로전달.
            // 예시 : ?bno=게시글번호
            redirectAttributes.addAttribute("itemId", shoesDTO.getItemId());
            return "redirect:/shoes/update" + pageRequestDTO.getLink();
        }
        log.info("화면에서 입력 받은 내용 update 확인 : " + shoesDTO);

        //화면 -> 서버 -> 서비스 -> 레포지토리 -> 디비, 입력후, 게시글 번호 가져오기
        //화면 <- 서버 <- 서비스 <- 레포지토리 <- 디비
        shoesService.update(shoesDTO);

        // 글쓰기 후, 작성된 게시글 번호 -> 화면 , 임시로 전달.(1회용)
        redirectAttributes.addFlashAttribute("result", shoesDTO.getItemId());
        redirectAttributes.addFlashAttribute("resultType", "update");


        return "redirect:/shoes/search?" + pageRequestDTO.getLink2();

    }

    @PostMapping("/delete")
    public String delete(ShoesDTO shoesDTO, PageRequestDTO pageRequestDTO, Long itemId, RedirectAttributes redirectAttributes
    ) {


        //화면 -> 서버 -> 서비스 -> 레포지토리 -> 디비, 입력후, 게시글 번호 가져오기
        //화면 <- 서버 <- 서비스 <- 레포지토리 <- 디비
        // 데이터베이스에서 , 댓글, 첨부된 이미지들도 다 삭제
        shoesService.deleteAll(itemId);
        log.info("delete : boardDTO 확인 : " + shoesDTO);
        // 미디어서버,  , 파일들도 다 같이 삭제.
        List<String> fileNames = shoesDTO.getFileNames();
        if (fileNames != null && fileNames.size() > 0) {
            removeFiles(fileNames);
        }

        // 글쓰기 후, 작성된 게시글 번호 -> 화면 , 임시로 전달.(1회용)
        redirectAttributes.addFlashAttribute("result", itemId);
        redirectAttributes.addFlashAttribute("resultType", "delete");
        return "redirect:/shoes/search?" + pageRequestDTO.getLink2();

    }
    public void removeFiles(List<String> files) {
        for (String fileName : files) {
            Resource resource = new FileSystemResource(
                    uploadPath + File.separator + fileName);
            String resourceName = resource.getFilename();
            // 결과 알려줄 임시 맵
//            Map<String,Boolean> resultMap = new HashMap<>();
            boolean deleteCheck = false;
            try {
                // 원본 파일 삭제
                // contentType , 섬네일 삭제시 이용할 예정.
                String contentType = Files.probeContentType(resource.getFile().toPath());
                deleteCheck = resource.getFile().delete();
                if (contentType.startsWith("image")) {
                    File thumbnailFile = new File(uploadPath + File.separator
                            + "s_" + fileName);
                    thumbnailFile.delete();
                }
            } catch (Exception e) {
                log.error(e.getMessage());
            }
//            resultMap.put("result",deleteCheck);
//            return resultMap;
        }
    }

}

package com.busanit501.shoesproject.dto.nhjdto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ReviewDTO {
    private Long rev_id;
    private Long item_id;
    private String member_id;
    // member id를 정수형으로 받을건지 string으로 받을지도 얘기해봐야할거같아요.
    private String member_name;
    private MultipartFile file;
    private String item_rev;
    // 리뷰를 여기 선언하게 된다면 범위(글 제한 수) 생각해봐야할 듯.
    private int item_rank;
    // 평점 추가할것인지 생각해봐야함.

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime regDate;
    @JsonIgnore
    private LocalDateTime modDate;
}

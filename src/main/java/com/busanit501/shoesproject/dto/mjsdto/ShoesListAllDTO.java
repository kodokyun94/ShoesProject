package com.busanit501.shoesproject.dto.mjsdto;

import com.busanit501.shoesproject.domain.mjsdomain.ShoesImage;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ShoesListAllDTO {
    private Long item_id;
    private String item_name;
    private String item_type;
    private String item_brand;
    private String item_price;
    private String item_review_rank_avg;
    private String item_gender;

    //댓글 갯수 표기 하기 위한 용도.
    private Long replyCount;
    // 첨부 이미지들
    private List<ShoesImageDTO> shoesImages;
}

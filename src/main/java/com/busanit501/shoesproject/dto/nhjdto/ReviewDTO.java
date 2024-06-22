package com.busanit501.shoesproject.dto.nhjdto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ReviewDTO {

    private long item_id;
    private long member_id;
    // member id를 정수형으로 받을건지 string으로 받을지도 얘기해봐야할거같아요.

    private String member_name;
    private String item_rev;
    // 리뷰를 여기 선언하게 된다면 범위(글 제한 수) 생각해봐야할 듯.

    private String item_rank;
    // 평점 추가할것인지 생각해봐야함
}

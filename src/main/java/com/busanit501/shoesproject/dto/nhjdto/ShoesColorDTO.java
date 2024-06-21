package com.busanit501.shoesproject.dto.nhjdto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ShoesColorDTO {

    private long color_id;
    private String item_color;
    

}

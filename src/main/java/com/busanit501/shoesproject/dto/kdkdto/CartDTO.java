package com.busanit501.shoesproject.dto.kdkdto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CartDTO {
    private Long cart_Id;
    private Long member_Id;
    private List<Long> item_Id;
}

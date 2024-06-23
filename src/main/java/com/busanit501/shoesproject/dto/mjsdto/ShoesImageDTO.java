package com.busanit501.shoesproject.dto.mjsdto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ShoesImageDTO {
    private String uuid;
    private String fileName;
    private int ord;
}






package com.busanit501.shoesproject.dto.kdkdto;

import com.busanit501.shoesproject.domain.kdkdomain.Cart;
import com.busanit501.shoesproject.domain.nhjdomain.Size;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CartItemDto {
    @NotNull(message = "상품 아이디는 필수 입력 값 입니다.")
    private Long itemId;

    @Min(value = 1, message = "최소 1개 이상 담아주세요")
    private int count;

}

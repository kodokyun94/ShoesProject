package com.busanit501.shoesproject.dto.kdkdto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class MemberDTO {
    private Long member_id;

    private String member_pw;
    private String member_name;
    private String member_email;
    private String member_phone;
    private String member_address;
}

package com.busanit501.shoesproject.dto.nhjdto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class MemberDTO {
    private String id;
    private String pw;
    private String name;
    private String phone;
    private String email;
    private String address;
}
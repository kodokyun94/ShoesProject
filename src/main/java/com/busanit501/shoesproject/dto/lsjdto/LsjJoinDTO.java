package com.busanit501.shoesproject.dto.lsjdto;


import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.*;


@Getter
@Setter
public class LsjJoinDTO {
//회원 가입 페이지로 부터 넘어오는 가입정보르 담은 DTO
    @NotEmpty
    @Size(min = 1, max = 50)
    private Long  member_id;

    @NotEmpty
    @Size(min = 1, max = 50)
    private String member_pw;

    @NotEmpty
    @Size(min = 1, max = 50)
    private String member_name;

    @NotEmpty
    @Size(min = 1, max = 50)
    private String member_email;

    @NotEmpty
    @Size(min = 1, max = 50)
    private String member_phone;

    @NotEmpty
    @Size(min = 1, max = 100)
    private String member_address;

}

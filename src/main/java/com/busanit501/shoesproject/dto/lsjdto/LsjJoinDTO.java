package com.busanit501.shoesproject.dto.lsjdto;


import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class LsjJoinDTO {

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






}

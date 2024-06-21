package com.busanit501.shoesproject.dto.nhjdto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

public class ReplyDTO { private Long rno;

    @NotNull
    private Long bno;
    @NotEmpty
    private String replyText;
    //    @NotEmpty
    private String replyer;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime regDate;
    @JsonIgnore
    private LocalDateTime modDate;
}

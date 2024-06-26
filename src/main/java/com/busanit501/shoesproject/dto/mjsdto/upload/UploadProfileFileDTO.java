package com.busanit501.shoesproject.dto.mjsdto.upload;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

@Data
public class UploadProfileFileDTO {
    private MultipartFile file;
}

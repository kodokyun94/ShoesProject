package com.busanit501.shoesproject.dto.lsjdto;

import lombok.Data;

import java.util.List;

@Data
public class MemberJoinDTO {
    private String member_id;
    private String member_pw;
    private String member_name;
    private boolean member_phone;
    private boolean member_email;
    private boolean member_address;

    private String uuid;
    private String fileName;
    //첨부 파일 이름들
    // fileNames, uuid_fileName 구조 되어 있고,
    // 업로드 후, 해당 dto에 위의 이름 구조 파일형식으로 업로드 하기.
    private List<String> fileNames;
    //    private MultipartFile profileImage;
    public void addProfileImage(String fileName) {
        this.fileNames.add(fileName);
    }
}

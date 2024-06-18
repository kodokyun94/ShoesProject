package com.busanit501.shoesproject.domain.lsjdomain;


import com.busanit501.shoesproject.constant.isjconstant.Role;
import com.busanit501.shoesproject.dto.lsjdto.LsjJoinDTO;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.security.crypto.password.PasswordEncoder;

@Entity
@Table(name = "shoes")
@Getter
@Setter
@ToString
public class LsjJoin {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long member_id;

    @Column(length = 50, nullable = false)
    private String member_pw;

    @Column(length = 50, nullable = false)
    private String member_name ;

    @Column(length = 50, nullable = false)
    private String member_phone;

    @Column(length = 50, nullable = false)
    private String member_email;

    @Column(length = 50, nullable = false)
    private String member_address;

    @Enumerated(EnumType.STRING)
    private Role role;

    public static LsjJoin createMember(LsjJoinDTO lsjJoinDTO, PasswordEncoder passwordEncoder) {

        LsjJoin lsjJoin = new LsjJoin();
        lsjJoin.setMember_id(lsjJoinDTO.getMember_id());
        lsjJoin.setMember_name(lsjJoinDTO.getMember_name());
        lsjJoin.setMember_email(lsjJoinDTO.getMember_email());
        lsjJoin.setMember_phone(lsjJoinDTO.getMember_phone());
        lsjJoin.setMember_address(lsjJoinDTO.getMember_address());
        String pw = passwordEncoder.encode(lsjJoinDTO.getMember_pw());
        lsjJoin.setMember_pw(pw);
        lsjJoin.setRole(Role.USER);
        return lsjJoin;

    }


}

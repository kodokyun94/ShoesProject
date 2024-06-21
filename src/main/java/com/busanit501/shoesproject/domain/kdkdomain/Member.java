//package com.busanit501.shoesproject.domain.kdkdomain;
//
//import jakarta.persistence.*;
//import lombok.*;
//
//import java.util.ArrayList;
//import java.util.List;
//
//@Builder
//@Getter
//@AllArgsConstructor
//@NoArgsConstructor
//@ToString
//// 이클래스는 실제 데이터베이스에 영향이 있다.asd
//// 반드시, pk 있어야 함.
//@Entity
//public class Member {
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private Long member_id;
//
//    private String member_pw;
//    private String member_name;
//    private String member_email;
//    private String member_phone;
//    private String member_address;
//
//    @OneToMany(mappedBy = "member", cascade = CascadeType.ALL)
//    private List<Cart> cart = new ArrayList<>();
//
//}
//

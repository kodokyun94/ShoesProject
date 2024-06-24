package com.busanit501.shoesproject.domain.nhjdomain;

import jakarta.persistence.*;
import lombok.*;
@Builder
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity
public class Color {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long colorId;

    @Column(length = 50,nullable = false)
    private String color;

}
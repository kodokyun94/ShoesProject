package com.busanit501.shoesproject.domain.nhjdomain;

import jakarta.persistence.*;
import lombok.*;

@Builder
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity
public class ShoesColor {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private long color_id;

    @Column(length = 50,nullable = false)
    private String item_color;

}

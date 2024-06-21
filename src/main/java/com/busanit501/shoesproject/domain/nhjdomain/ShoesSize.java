package com.busanit501.shoesproject.domain.nhjdomain;

import jakarta.persistence.*;
import lombok.*;

@Builder
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity
public class ShoesSize {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private long size_id;

    @Column(length=50, nullable = false)
    private String item_size;

}

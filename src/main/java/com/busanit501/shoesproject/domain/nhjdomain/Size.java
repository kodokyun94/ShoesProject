package com.busanit501.shoesproject.domain.nhjdomain;

import jakarta.persistence.*;
import lombok.*;

@Builder
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity
public class Size {

    @Id
    private Long sizeId;

    @Column(length = 50,nullable = false)
    private String size;

}
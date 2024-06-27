package com.busanit501.shoesproject.domain.nhjdomain;

import com.busanit501.shoesproject.domain.kdkdomain.CartItem;
import com.busanit501.shoesproject.domain.kdkdomain.Item;
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

    @ManyToOne
    @JoinColumn(name = "item_id")
    private Item item;


}
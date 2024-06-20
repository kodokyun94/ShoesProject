package com.busanit501.shoesproject.domain.kdkdomain;

import jakarta.persistence.*;
import lombok.*;

@Builder
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
// 이클래스는 실제 데이터베이스에 영향이 있다.
// 반드시, pk 있어야 함.
@Entity
public class Item {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long item_id;

    @Column(nullable = false, length = 50)
    private String item_name;

    @Column(name="price", nullable = false)
    private Long item_price;

    @Column(nullable = false)
    private int item_stockNumber; //재고수량

    @Lob
    @Column(nullable = false)
    private String itemDetail; //상품 상세 설명






}
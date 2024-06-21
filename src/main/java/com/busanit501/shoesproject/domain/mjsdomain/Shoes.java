package com.busanit501.shoesproject.domain.mjsdomain;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.BatchSize;

import java.util.HashSet;
import java.util.Set;

@Builder
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity
public class Shoes {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long item_id;

    @Column(length = 50,nullable = false)
    private String item_name;

    @Column(length = 50,nullable = false)
    private String item_type;

    @Column(length = 50,nullable = false)
    private String item_brand;

    @Column(length = 500,nullable = false)
    private String item_price;

    @Column(length = 50,nullable = false)
    private String item_review_rank_avg;

    @Column(length = 50,nullable = false)
    private String item_gender;

    @OneToMany(mappedBy = "shoes",
            // 부모 테이블의 1차 캐시 테이블에 작업시, 하위 테이블에도 다 적용함.
            cascade = {CascadeType.ALL},
            // 데이터베이스 조금 늦게 반영하겠다.
            // 기본값은 FetchType.EAGER 즉시로딩인데 변경함
            fetch = FetchType.LAZY,
            // 데이터베이스에서, 부모 보드 번호 null, 삭제 하기.
            orphanRemoval = true)
    @Builder.Default
    // N + 1, 부모 테이블을 조회시, 각 자식테이블(첨부이미지 테이블), 각각 하나씩 조회를 함.
    // 매번 디비 연결하는 자원 소모가 발생을함.
    // 해결책 . @BatchSize( size = 20), 지정된 갯수만큼 모아서, 한번에 처리함.
    @BatchSize(size = 20)
    private Set<ShoesImage> imageSet = new HashSet<>();

    //이미지들 추가
    public void addImage(String uuid, String fileName) {
        ShoesImage shoesImage = ShoesImage.builder()
                .uuid(uuid)
                .fileName(fileName)
                .shoes(this)
                .ord(imageSet.size())
                .build();

        imageSet.add(shoesImage);
    }

    // 이미지들 삭제
    public void clearImages(){
        // 보드 엔티티 , 멤버에 보드 이미지들을 가지고 있는 set 목록
        // 목록 요소가 보드이미지, 각 보드 이미지에는 부모 테이블 보드의 참조형 변수 0x100
        // 삭제시, 보드이미지의 멤버인 board 객체를 0x100 -> null 변경.
        // 부모 테이블이 참조형 변수가 없어져서, 고아 객체가 됨. 자동 수거 함. , 삭제 기능.
        imageSet.forEach(shoesImage -> shoesImage.changeShoes(null));
        this.imageSet.clear();
    }

    public void changeall(String[] itemDetails) {
        if (itemDetails.length != 6) {
            throw new IllegalArgumentException("7개의 값이 필요합니다.");
        }
        this.item_name = itemDetails[0];
        this.item_brand = itemDetails[1];
        this.item_type = itemDetails[2];
        this.item_price = itemDetails[3];
        this.item_review_rank_avg = itemDetails[4];
        this.item_gender = itemDetails[5];
    }



}

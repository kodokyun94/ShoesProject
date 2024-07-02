package com.busanit501.shoesproject.domain.kdkdomain;

import com.busanit501.shoesproject.constant.ItemSellStatus;
import com.busanit501.shoesproject.dto.kdkdto.ItemFormDto;
import com.busanit501.shoesproject.exception.OutOfStockException;
import jakarta.persistence.*;
import lombok.*;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity
@Table(name = "item")
public class Item extends BaseEntity {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long itemId;

    @Column(length=50, nullable = false)
    private String itemName;

    @Column(length=50, nullable = false)
    private String itemType;

    @Column(length=50, nullable = false)
    private String itemBrand;

    @Column(length=50, nullable = false)
    private int itemPrice;

    //@Column(length=50, nullable = false)
    //private String review_rank_avg;

    @Column(nullable = false)
    private int stockNumber; //재고수량

    @Column(length=50, nullable = false)
    private String itemGender;

    @Lob
    @Column(nullable = false)
    private String itemDetail; //상품 상세 설명


    @Enumerated(EnumType.STRING)
    private ItemSellStatus itemSellStatus; //상품 판매 상태

    public void updateItem(ItemFormDto itemFormDto){
        this.itemName = itemFormDto.getItemNm();
        this.itemPrice = itemFormDto.getPrice();
        this.stockNumber = itemFormDto.getStockNumber();
        this.itemDetail = itemFormDto.getItemDetail();
        this.itemSellStatus = itemFormDto.getItemSellStatus();
    }

    public void removeStock(int stockNumber){
        int restStock = this.stockNumber - stockNumber;
        if(restStock<0){
            throw new OutOfStockException("상품의 재고가 부족 합니다. (현재 재고 수량: " + this.stockNumber + ")");
        }
        this.stockNumber = restStock;
    }

    public void addStock(int stockNumber){
        this.stockNumber += stockNumber;
    }

}
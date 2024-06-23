package com.busanit501.shoesproject.domain.mjsdomain;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.*;

@Entity
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString(exclude = "shoes")
public class ShoesImage implements Comparable<ShoesImage>{
    @Id
    private String uuid;

    private String fileName;

    private int ord;

    @ManyToOne
    private Shoes shoes;

    @Override
    public int compareTo(ShoesImage other) {
        return ord - other.ord;
    }
    public void changeShoes(Shoes shoes) {this.shoes = shoes;}
}

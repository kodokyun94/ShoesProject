package com.busanit501.shoesproject.domain.kdkdomain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "cart")
@Getter @Setter
@ToString
public class Cart {

    @Id
    @Column(name = "cart_id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long cart_id;

    @OneToMany
    @JoinColumn(name = "member_id")
    private Member member;

}

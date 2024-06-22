package com.bao.baocard.modules.card.model;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "cards")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Card {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "card_id")
    private Long id;

    private String name;

    private String image;

    @Column(name = "annual_fee")
    private String annualFee;

    @Column(name = "pre_month_performance")
    private String preMonthPerformance;

    private String benefits;
}

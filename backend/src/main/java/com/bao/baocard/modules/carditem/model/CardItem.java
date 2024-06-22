package com.bao.baocard.modules.carditem.model;

import com.bao.baocard.modules.card.model.Card;
import com.bao.baocard.modules.cardorder.CardOrder;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "card_items")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class CardItem {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "card_item_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "card_order_id")
    private CardOrder cardOrder;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "card_id")
    private Card card;

    // 정적 팩토리 메서드
    public static CardItem createCardItem(Card card) {
        CardItem memberCard = new CardItem();
        memberCard.card = card;
        return memberCard;
    }

    public void updateCardOrder(CardOrder cardOrder) {
        this.cardOrder = cardOrder;
    }
}

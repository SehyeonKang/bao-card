package com.bao.baocard.modules.cardorder;

import com.bao.baocard.modules.carditem.model.CardItem;
import com.bao.baocard.modules.member.model.Member;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "card_orders")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class CardOrder {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "card_order_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    @OneToMany(mappedBy = "cardOrder", cascade = CascadeType.ALL)
    private List<CardItem> cardItems = new ArrayList<>();

    @Column(name = "order_date")
    private LocalDateTime orderDate;

    // 정적 팩토리 메서드
    public static CardOrder createCardOrder(Member member, CardItem... cardItems) {
        CardOrder cardOrder = new CardOrder();
        cardOrder.member = member;
        for (CardItem cardItem : cardItems) {
            cardOrder.addCardItem(cardItem);
        }
        cardOrder.orderDate = LocalDateTime.now();
        return cardOrder;
    }

    // 연관관계 메서드
    public void addMember(Member member) {
        this.member = member;
        member.getCardOrders().add(this);
    }

    public void addCardItem(CardItem cardItem) {
        cardItems.add(cardItem);
        cardItem.updateCardOrder(this);
    }
}

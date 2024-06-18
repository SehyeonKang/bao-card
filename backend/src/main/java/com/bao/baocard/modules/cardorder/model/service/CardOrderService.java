package com.bao.baocard.modules.cardorder.model.service;

import com.bao.baocard.infra.util.SecurityUtils;
import com.bao.baocard.modules.card.Card;
import com.bao.baocard.modules.card.model.repository.CardRepository;
import com.bao.baocard.modules.carditem.model.CardItem;
import com.bao.baocard.modules.cardorder.CardOrder;
import com.bao.baocard.modules.cardorder.model.repository.CardOrderRepository;
import com.bao.baocard.modules.member.model.Member;
import com.bao.baocard.modules.member.model.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CardOrderService {

    private final CardOrderRepository cardOrderRepository;
    private final MemberRepository memberRepository;
    private final CardRepository cardRepository;

    /**
     * 유저가 카드를 주문하는 메서드
     * @param cardId
     * @return
     */
    @Transactional
    public Long orderCard(Long cardId) {
        Member member = memberRepository.findById(SecurityUtils.getCurrentMemberId()).get();
        Card card = cardRepository.findById(cardId).get();

        CardItem cardItem = CardItem.createCardItem(card);
        CardOrder cardOrder = CardOrder.createCardOrder(member, cardItem);

        cardOrderRepository.save(cardOrder);
        return cardOrder.getId();
    }

//    @Transactional(readOnly = true)
//    public List<CardOrder> findCardOrders() {
//    }

}

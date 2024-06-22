package com.bao.baocard.modules.cardorder.model.service;

import com.bao.baocard.infra.util.SecurityUtils;
import com.bao.baocard.modules.card.model.Card;
import com.bao.baocard.modules.card.model.repository.CardRepository;
import com.bao.baocard.modules.carditem.model.CardItem;
import com.bao.baocard.modules.cardorder.CardOrder;
import com.bao.baocard.modules.cardorder.model.dto.CardOrderDto;
import com.bao.baocard.modules.cardorder.model.dto.CardOrderResponseDto;
import com.bao.baocard.modules.cardorder.model.repository.CardOrderRepository;
import com.bao.baocard.modules.member.model.Member;
import com.bao.baocard.modules.member.model.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
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
     * @param cardIds
     * @return
     */
    @Transactional
    public Long orderCard(List<Long> cardIds) {
        Member member = memberRepository.findById(SecurityUtils.getCurrentMemberId()).get();

        List<CardItem> cardItems = cardIds.stream()
                .map(cardId -> {
                    Card card = cardRepository.findById(cardId)
                            .orElseThrow(() -> new IllegalArgumentException("카드 정보를 찾을 수 없습니다."));
                    return CardItem.createCardItem(card);
                })
                .toList();

        CardOrder cardOrder = CardOrder.createCardOrder(member, cardItems.toArray(new CardItem[0]));

        cardOrderRepository.save(cardOrder);

        return cardOrder.getId();
    }

    /**
     * 유저가 주문한 카드 목록 조회
     * @return
     */
    @Transactional(readOnly = true)
    public CardOrderResponseDto findCardOrders(int offset, int limit) {
        Page<CardOrder> cardOrdersPage = cardOrderRepository.findCardOrdersByMemberId(SecurityUtils.getCurrentMemberId(),
                PageRequest.of(offset, limit));
        List<CardOrder> cardOrders = cardOrdersPage.getContent();
        List<CardOrderDto> result = cardOrders.stream()
                .map(o -> new CardOrderDto(o))
                .toList();

        return new CardOrderResponseDto(result, cardOrdersPage.getTotalElements(), cardOrdersPage.getTotalPages(),
                cardOrdersPage.getSize(), cardOrdersPage.getNumber(), cardOrdersPage.isFirst(), cardOrdersPage.isLast());
    }

}

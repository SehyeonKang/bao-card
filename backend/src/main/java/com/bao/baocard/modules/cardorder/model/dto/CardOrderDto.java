package com.bao.baocard.modules.cardorder.model.dto;

import com.bao.baocard.modules.carditem.model.dto.CardItemDto;
import com.bao.baocard.modules.cardorder.CardOrder;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
public class CardOrderDto {

    private Long cardOrderId;
    private LocalDateTime orderDate;
    private List<CardItemDto> cardItems;

    public CardOrderDto(CardOrder cardOrder) {
        cardOrderId = cardOrder.getId();
        orderDate = cardOrder.getOrderDate();
        cardItems = cardOrder.getCardItems().stream()
                .map(cardItem -> new CardItemDto(cardItem))
                .toList();
    }
}

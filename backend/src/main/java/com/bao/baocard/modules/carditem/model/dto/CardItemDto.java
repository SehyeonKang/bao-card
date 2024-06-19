package com.bao.baocard.modules.carditem.model.dto;

import com.bao.baocard.modules.carditem.model.CardItem;
import lombok.Data;

@Data
public class CardItemDto {

    private Long cardId;
    private String cardName;
    private String cardImage;

    public CardItemDto(CardItem cardItem) {
        cardId = cardItem.getCard().getId();
        cardName = cardItem.getCard().getName();
        cardImage = cardItem.getCard().getImage();
    }
}

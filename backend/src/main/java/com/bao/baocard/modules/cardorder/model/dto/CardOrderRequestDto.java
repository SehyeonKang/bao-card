package com.bao.baocard.modules.cardorder.model.dto;

import lombok.Data;

import java.util.List;

@Data
public class CardOrderRequestDto {

    private List<Long> cardIds;
}

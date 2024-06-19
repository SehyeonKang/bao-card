package com.bao.baocard.modules.cardorder.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class CardOrderResponseDto {

    private List<CardOrderDto> cardOrders;
    private Long totalElements;
    private Integer totalPages;
    private Integer size;
    private Integer number;
    private Boolean isFirst;
    private Boolean isLast;
}

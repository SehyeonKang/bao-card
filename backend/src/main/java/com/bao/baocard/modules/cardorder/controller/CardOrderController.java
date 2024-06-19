package com.bao.baocard.modules.cardorder.controller;

import com.bao.baocard.modules.cardorder.model.dto.CardOrderRequestDto;
import com.bao.baocard.modules.cardorder.model.dto.CardOrderResponseDto;
import com.bao.baocard.modules.cardorder.model.service.CardOrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/order")
@RequiredArgsConstructor
public class CardOrderController {

    private final CardOrderService cardOrderService;

    @PostMapping
    public ResponseEntity<?> orderCards(@RequestBody CardOrderRequestDto request) {
        cardOrderService.orderCard(request.getCardIds());
        return ResponseEntity.ok().build();
    }

    @GetMapping
    public ResponseEntity<CardOrderResponseDto> getCardOrders(@RequestParam(value = "offset", defaultValue = "0") int offset,
                                                              @RequestParam(value = "limit", defaultValue = "10") int limit ) {
        CardOrderResponseDto cardOrders = cardOrderService.findCardOrders(offset, limit);
        return ResponseEntity.ok(cardOrders);
    }
}

package com.bao.baocard.modules.cardorder.controller;

import com.bao.baocard.modules.cardorder.model.dto.CardOrderRequestDto;
import com.bao.baocard.modules.cardorder.model.service.CardOrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class CardOrderController {

    private final CardOrderService cardOrderService;

    @PostMapping("/order")
    public ResponseEntity<?> cardOrder(@RequestBody CardOrderRequestDto request) {
        cardOrderService.orderCard(request.getCardId());
        return ResponseEntity.ok().build();
    }

}

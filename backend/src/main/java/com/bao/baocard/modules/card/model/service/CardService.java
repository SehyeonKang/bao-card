package com.bao.baocard.modules.card.model.service;

import com.bao.baocard.infra.util.SecurityUtils;
import com.bao.baocard.modules.card.model.Card;
import com.bao.baocard.modules.card.model.repository.CardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CardService {

    private final CardRepository cardRepository;

    @Transactional(readOnly = true)
    public List<Card> findCardsByMemberId() {
        return cardRepository.findCardsByMemberId(SecurityUtils.getCurrentMemberId());
    }
}

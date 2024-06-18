package com.bao.baocard.modules.card.model.repository;

import com.bao.baocard.modules.card.Card;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CardRepository extends JpaRepository<Card, Long> {
}

package com.bao.baocard.modules.carditem.model.repository;

import com.bao.baocard.modules.carditem.model.CardItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CardItemRepository extends JpaRepository<CardItem, Long> {
}

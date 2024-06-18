package com.bao.baocard.modules.cardorder.model.repository;

import com.bao.baocard.modules.cardorder.CardOrder;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CardOrderRepository extends JpaRepository<CardOrder, Long> {

    List<CardOrder> findCardOrdersByMemberId(Long memberId);
}

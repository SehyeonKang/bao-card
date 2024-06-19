package com.bao.baocard.modules.cardorder.model.repository;

import com.bao.baocard.modules.cardorder.CardOrder;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface CardOrderRepository extends JpaRepository<CardOrder, Long> {

    @Query("select co from CardOrder co")
    Page<CardOrder> findCardOrdersByMemberId(Long memberId, Pageable pageable);
}

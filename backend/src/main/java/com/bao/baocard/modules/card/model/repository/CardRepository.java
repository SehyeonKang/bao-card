package com.bao.baocard.modules.card.model.repository;

import com.bao.baocard.modules.card.model.Card;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CardRepository extends JpaRepository<Card, Long> {

    @Query("select distinct c from CardOrder co join co.cardItems ci join ci.card c where co.member.id = :memberId")
    List<Card> findCardsByMemberId(@Param("memberId") Long memberId);
}

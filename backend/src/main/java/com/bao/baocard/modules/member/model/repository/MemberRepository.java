package com.bao.baocard.modules.member.model.repository;

import com.bao.baocard.modules.member.model.Member;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<Member, Long> {
    boolean existsByLoginId(String loginId);
}

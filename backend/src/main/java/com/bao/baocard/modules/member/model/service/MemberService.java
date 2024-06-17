package com.bao.baocard.modules.member.model.service;

import com.bao.baocard.modules.member.model.Member;
import com.bao.baocard.modules.member.model.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;

    @Transactional
    public Member registerMember(String email, String password, String nickname) {
        if (memberRepository.existsByEmail(email)) {
            throw new IllegalArgumentException("이미 존재하는 이메일입니다.");
        }
        Member member = new Member(email, password, nickname);
        return memberRepository.save(member);
    }
}

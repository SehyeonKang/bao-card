package com.bao.baocard.modules.member.controller;

import com.bao.baocard.modules.member.model.Member;
import com.bao.baocard.modules.member.model.dto.MemberRegistrationRequest;
import com.bao.baocard.modules.member.model.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/members")
public class MemberController {

    private final MemberService memberService;

    @PostMapping("/register")
    public ResponseEntity<Member> register(@RequestBody MemberRegistrationRequest request) {
        Member member = memberService.registerMember(request.getEmail(), request.getPassword(), request.getNickname());
        return ResponseEntity.ok(member);
    }
}

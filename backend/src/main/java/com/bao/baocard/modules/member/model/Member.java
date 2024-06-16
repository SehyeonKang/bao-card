package com.bao.baocard.modules.member.model;

import jakarta.persistence.*;
import lombok.Getter;

@Entity
@Getter
@Table(name = "members")
public class Member {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "member_id")
    private Long id;

    private String loginId;
    private String password;
    private String nickname;

    protected Member() {}

    public Member(String loginId, String password, String nickname) {
        this.loginId = loginId;
        this.password = password;
        this.nickname = nickname;
    }
}

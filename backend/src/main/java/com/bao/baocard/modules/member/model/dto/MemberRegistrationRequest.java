package com.bao.baocard.modules.member.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MemberRegistrationRequest {

    private String email;
    private String password;
    private String nickname;
}

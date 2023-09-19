package com.example.dnlab.dto.user;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class LoginTokenResDto {
    private String uid;
    private String accessToken;
    private String refreshToken;
}

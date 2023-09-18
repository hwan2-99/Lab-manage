package com.example.dnlab.dto.user;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class LoginReqDto {
    private String uid;
    private String pw;
}

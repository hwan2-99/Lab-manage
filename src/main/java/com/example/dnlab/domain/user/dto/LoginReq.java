package com.example.dnlab.domain.user.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
public class LoginReq {
    private String id;
    private String pw;
}

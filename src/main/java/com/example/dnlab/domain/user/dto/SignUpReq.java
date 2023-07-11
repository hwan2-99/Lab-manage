package com.example.dnlab.domain.user.dto;

import lombok.Getter;

@Getter
public class SignUpReq {
    private String name;
    private int studentId;
    private String id;
    private String pw;
}

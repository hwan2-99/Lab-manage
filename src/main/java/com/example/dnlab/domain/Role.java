package com.example.dnlab.domain;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public enum Role {
    PROFESSOR("ROLE_PROFESSOR"),
    MANAGER("ROLE_MANAGER"),
    RESEARCHER("ROLE_RESEARCHER"),
    MEMBER("ROLE_MEMBER");

    private final String roleName;
}

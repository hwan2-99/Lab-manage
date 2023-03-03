package com.example.dnlab.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@Getter
@NoArgsConstructor
public class User {
    private String name;
    private String id;
    private String pw;

    public User(String id) {
        this.id = id;
    }
}

package com.example.dnlab.domain.user.entity;

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
    private Integer generation;
    private boolean leaderYN;
    public User(String id) {
        this.id = id;
    }

    public User(String name, String id, Integer generation, String pw, boolean leaderYN) {
        this.id = id;
        this.generation = 0;
        this.pw = pw;
        this.leaderYN = false;
    }
}

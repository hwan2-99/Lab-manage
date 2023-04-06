package com.example.dnlab.domain.user.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class User {
    private int num;  // 회원의 pk
    private String name; //이름
    private int studentId; //학번
    private String id; //아이디
    private String pw; //비밀번호
    private String generation; //기수
    private boolean leaderYN; // 연구실장 유무

    public User(String name, int studentId, String id, String pw) {
        this.name = name;
        this.studentId = studentId;
        this.id = id;
        this.pw = pw;
    }
}

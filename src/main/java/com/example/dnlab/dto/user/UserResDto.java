package com.example.dnlab.dto.user;

import lombok.Builder;
import lombok.Getter;

import javax.persistence.Column;

@Getter
@Builder
public class UserResDto {
    private int num;  // 회원의 pk
    private String name; //이름
    private int studentId; //학번
    private String id; //아이디
    private int generation; //기수
    private boolean leaderYN; // 연구실장 유무
}

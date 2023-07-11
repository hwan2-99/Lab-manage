package com.example.dnlab.domain.user.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Builder
@NoArgsConstructor
public class MyPageRes {
    private String name;
    private int studentId;
    private int generation;

    public MyPageRes(String name, int studentId, int generation) {
        this.name = name;
        this.studentId = studentId;
        this.generation = generation;
    }
}

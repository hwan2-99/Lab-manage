package com.example.dnlab.dto.user;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Builder
@NoArgsConstructor
public class MyPageResDto {
    private String name;
    private int studentId;
    private int generation;

    public MyPageResDto(String name, int studentId, int generation) {
        this.name = name;
        this.studentId = studentId;
        this.generation = generation;
    }
}
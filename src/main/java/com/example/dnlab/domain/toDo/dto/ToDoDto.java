package com.example.dnlab.domain.toDo.dto;


import lombok.*;

public class ToDoDto {
    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    public static class createReq{
        private String content; //할일 내용
    }

    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    public static class updateReq{
        private String content; //할일 내용
    }
}

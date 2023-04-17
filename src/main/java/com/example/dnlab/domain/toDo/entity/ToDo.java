package com.example.dnlab.domain.toDo.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ToDo {
    private int num;  // to-do-list 의 pk
    private int user_num;  // to-do-list 등록 유저 fk
    private String content; //할일 내용
    private LocalDate week_start_date; //시작 날
    private String status; //할일 상태
    private Timestamp createdAt; // 생성 날짜

    public ToDo(String content, int userNum) {
        this.content = content;
        this.user_num = userNum;
    }

    public ToDo(String content, int user_num, LocalDate week_start_date) {
        this.content = content;
        this.user_num = user_num;
        this.week_start_date = week_start_date;
    }
}

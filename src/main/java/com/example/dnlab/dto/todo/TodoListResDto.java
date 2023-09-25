package com.example.dnlab.dto.todo;

import com.example.dnlab.domain.Todo;
import com.example.dnlab.domain.TodoStatus;
import lombok.Getter;

import java.sql.Timestamp;
import java.time.LocalDate;

@Getter
public class TodoListResDto {
    private int id;  // to-do-list 의 pk
    private String content; //할일 내용
    private LocalDate weekStartDate; //시작 날
    private TodoStatus status; //할일 상태
    private Timestamp createdAt; // 생성 날짜
    private String userName;

    public TodoListResDto(Todo entity) {
        this.id = entity.getId();
        this.content = entity.getContent();
        this.weekStartDate = entity.getWeekStartDate();
        this.status = entity.getStatus();
        this.createdAt = entity.getCreatedAt();
        this.userName = entity.getUser().getName();
    }


}

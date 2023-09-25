package com.example.dnlab.dto.todo;

import com.example.dnlab.domain.TodoStatus;
import lombok.Builder;
import lombok.Getter;
import java.sql.Timestamp;
import java.time.LocalDate;

@Getter
@Builder
public class TodoResDto {

    private int id;  // to-do-list 의 pk
    private String content; //할일 내용
    private LocalDate weekStartDate; //시작 날
    private TodoStatus status; //할일 상태
    private Timestamp createdAt; // 생성 날짜
    private int userId;
}

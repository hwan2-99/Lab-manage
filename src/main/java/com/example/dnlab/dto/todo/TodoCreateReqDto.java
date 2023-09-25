package com.example.dnlab.dto.todo;

import com.example.dnlab.domain.Todo;
import com.example.dnlab.domain.User;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.DayOfWeek;
import java.time.LocalDate;

@Getter
@Builder
public class TodoCreateReqDto {
    private String content; //할일 내용
    LocalDate today = LocalDate.now();
    LocalDate monday = today.with(DayOfWeek.MONDAY);

    public Todo toEntity(User user){
        return Todo.builder()
                .user(user)
                .content(content)
                .weekStartDate(monday)
                .build();

    }
}

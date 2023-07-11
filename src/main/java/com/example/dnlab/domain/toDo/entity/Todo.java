package com.example.dnlab.domain.toDo.entity;

import com.example.dnlab.domain.user.entity.User;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.sql.Timestamp;
import java.time.LocalDate;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Builder
public class Todo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "num")
    private int num;  // to-do-list 의 pk
    @Column(columnDefinition = "TEXT")
    private String content; //할일 내용
    @Column
    private LocalDate weekStartDate; //시작 날
    @Column
    @Enumerated(EnumType.STRING)
    private TodoStatus status; //할일 상태
    @Column
    private Timestamp createdAt; // 생성 날짜

    @JoinColumn(name = "user_num")
    @JsonIgnore
    @ManyToOne
    private User user;
}



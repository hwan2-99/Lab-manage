package com.example.dnlab.domain;

import com.example.dnlab.domain.TodoStatus;
import com.example.dnlab.domain.User;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.sql.Timestamp;
import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Builder
public class Todo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;  // to-do-list 의 pk
    @Column(columnDefinition = "TEXT")
    private String content; //할일 내용
    @Column
    private LocalDate weekStartDate; //시작 날
    @Column
    @Enumerated(EnumType.STRING)
    private TodoStatus status; //할일 상태
    @Column
    private Timestamp createdAt; // 생성 날짜

    @JoinColumn(name = "user_id")
    @JsonIgnore
    @ManyToOne
    private User user;
}



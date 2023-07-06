package com.example.dnlab.domain.toDo.entity;

import com.example.dnlab.domain.user.entity.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class ToDo {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "num")
    private int num;  // to-do-list 의 pk
    @Column
    private String content; //할일 내용
    @Column
    private LocalDate week_start_date; //시작 날
    @Column
    private String status; //할일 상태
    @Column
    private Timestamp createdAt; // 생성 날짜

    @JoinColumn(name = "num")
    @ManyToOne
    private User user;

}



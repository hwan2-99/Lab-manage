package com.example.dnlab.domain.application.entity;

import com.example.dnlab.domain.user.entity.User;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Builder
public class Application {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "num")
    private int num; // pk
    @Column
    private String motive; //지원동기
    @Column
    private String intro; // 자기소개
    @Column
    private String wanted; // 가입 후 하고싶은 연구활동
    @Column
    private LocalDate createdAt; // 신청서 작성날짜
    @Column
    private LocalDate updatedAt; // 신청서 수정날짜
    @Column
    @Enumerated(EnumType.STRING)
    private ApplicationStatus status; // 신청서 승낙여부
    @JoinColumn(name = "user_num")
    @ManyToOne
    private User user;

}

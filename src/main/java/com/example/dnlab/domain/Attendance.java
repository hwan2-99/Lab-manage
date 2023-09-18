package com.example.dnlab.domain;

import lombok.*;

import javax.persistence.*;
import java.util.Date;


@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Builder
public class Attendance {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id; // 출석 pk
    @Column
    @Enumerated(EnumType.STRING)
    private AttendanceStatus status; // 출석 상태;
    @Column
    private Date startTime; // 출근 시작 시간
//    private Date endTime; // 퇴근 시간

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

}

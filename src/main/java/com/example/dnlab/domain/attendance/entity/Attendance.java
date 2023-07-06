package com.example.dnlab.domain.attendance.entity;

import com.example.dnlab.domain.user.entity.User;
import lombok.*;

import javax.persistence.*;
import java.util.Date;


@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Attendance {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "num")
    private int num; // 출석 pk
    @Column
    @Enumerated(EnumType.STRING)
    private AttendanceStatus status; // 출석 상태;
    @Column
    private Date startTime; // 출근 시작 시간
//    private Date endTime; // 퇴근 시간


    @ManyToOne
    @JoinColumn(name = "user_num")
    private User user;

    public Attendance(int num, AttendanceStatus status, Date startTime) {
        this.num = num;
        this.status = status;
        this.startTime = startTime;
    }
    public Attendance(Date startTime, AttendanceStatus status) {
        this.status = status;
        this.startTime = startTime;
    }

    public void setUserName(String name) {
    }
}

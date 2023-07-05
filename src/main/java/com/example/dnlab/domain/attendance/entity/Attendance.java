package com.example.dnlab.domain.attendance.entity;

import com.example.dnlab.domain.attendance.AttendanceStatus;
import com.example.dnlab.domain.user.entity.User;
import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Date;


@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Attendance {
    @Id
    @Column(name = "num")
    private int num; // 출석 pk
    private int user_num; // 유저 외래키
    private AttendanceStatus status; // 출석 상태;
    private Date startTime; // 출근 시작 시간
//    private Date endTime; // 퇴근 시간

    public Attendance(int user_num, AttendanceStatus status, Date startTime) {
        this.user_num = user_num;
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

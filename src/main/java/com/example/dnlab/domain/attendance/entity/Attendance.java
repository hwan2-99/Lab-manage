package com.example.dnlab.domain.attendance.entity;

import com.example.dnlab.domain.attendance.AttendanceStatus;
import com.example.dnlab.domain.user.entity.User;
import lombok.*;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Attendance {
    private int num; // 출석 pk
    private User user;
    private int userNum; // 유저 외래키
    private AttendanceStatus status; // 출석 상태;
    private Date startTime; // 출근 시작 시간
    private Date endTime; // 퇴근 시간

    public Attendance(int userNum, AttendanceStatus status, Date startTime) {
        this.userNum = userNum;
        this.status = status;
        this.startTime = startTime;
    }

    public Attendance(int num, int userNum, AttendanceStatus status, Date startTime) {
        this.num = num;
        this.userNum = userNum;
        this.status = status;
        this.startTime = startTime;
    }

    public void setUserName(String name) {
    }
}

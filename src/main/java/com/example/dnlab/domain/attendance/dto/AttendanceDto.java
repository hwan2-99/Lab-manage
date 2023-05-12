package com.example.dnlab.domain.attendance.dto;

import com.example.dnlab.domain.attendance.AttendanceStatus;
import com.example.dnlab.domain.user.entity.User;
import lombok.*;

import java.util.Date;

public class AttendanceDto {
    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    public static class StartCheck{
        private Date startTime; // 출근시간
    }

    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    public static class EndCheck{
        private Date EndTime; // 퇴근시간
    }
    @Getter 
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    public static class AttendanceCheck{
        private int num; // 출석 pk
        private int userNum; // 유저 외래키
        private AttendanceStatus status; // 출석 상태;
        private Date startTime; // 출근 시작 시간
        private String userName; // 유저이름
        private int attendanceCount; // 출석 일수
    }
}

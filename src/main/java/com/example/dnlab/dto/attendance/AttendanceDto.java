package com.example.dnlab.dto.attendance;

import com.example.dnlab.domain.AttendanceStatus;
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
    public static class AttendanceCheck{
        private int num; // 출석 pk
        private int user_num; // 유저 외래키
        private AttendanceStatus status; // 출석 상태;
        private Date startTime; // 출근 시작 시간
        private String userName; // 유저이름
        private int attendanceCount; // 출석 일수
    }
}

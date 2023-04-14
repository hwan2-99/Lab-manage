package com.example.dnlab.domain.attendance.dto;

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
}

package com.example.dnlab.dto.attendance;

import com.example.dnlab.domain.AttendanceStatus;
import lombok.Builder;
import lombok.Getter;

import java.util.Date;

@Getter
@Builder
public class AttendanceResDto {
    private int num; // 출석 pk
    private int userId; // 유저 외래키
    private AttendanceStatus status; // 출석 상태;
    private Date startTime; // 출근 시작 시간
    private String userName; // 유저이름
    private int attendanceCount; // 출석 일수
}

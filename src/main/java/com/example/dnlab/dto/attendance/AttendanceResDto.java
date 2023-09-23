package com.example.dnlab.dto.attendance;

import com.example.dnlab.domain.AttendanceStatus;
import lombok.Builder;
import lombok.Getter;

import java.util.Date;

@Getter
@Builder
public class AttendanceResDto {
    private AttendanceStatus status; // 출석 상태;
    private Date startTime; // 출근 시작 시간
}

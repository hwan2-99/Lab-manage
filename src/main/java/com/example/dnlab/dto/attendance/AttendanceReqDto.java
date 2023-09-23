package com.example.dnlab.dto.attendance;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Date;

@Getter
@Data
public class AttendanceReqDto {
    private Date startTime; // 출근시간
}

package com.example.dnlab.domain.attendance.scheduler;

import com.example.dnlab.domain.attendance.service.AttendanceService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class AttendanceScheduler {

    private final AttendanceService attendanceService;

    public AttendanceScheduler(AttendanceService attendanceService) {
        this.attendanceService = attendanceService;
    }

    @Scheduled(cron = "0 0 22 * * *") // 매일 자정에 실행
    public void checkAndAddAbsence() {
        log.info("결석 처리");
        attendanceService.checkAndAddAbsence();
    }
}
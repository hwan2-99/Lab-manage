package com.example.dnlab.domain;

import com.example.dnlab.service.AttendanceService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.Map;

@Slf4j
@Component
public class AttendanceScheduler {

    private final AttendanceService attendanceService;

    public AttendanceScheduler(AttendanceService attendanceService) {
        this.attendanceService = attendanceService;
    }

    @Scheduled(cron = "0 0 21 * * 1-5") // 월요일부터 토요일 자정 전까지 매일 오후 9시에 실행
    public void checkAndAddAbsence() {
        log.info("결석 처리");
        attendanceService.checkAndAddAbsence();
    }

    @Scheduled(cron = "0 0 0 1 * ?") // 매월 1일 자정에 실행
    public void generateMonthlyAttendanceStats() {
        log.info("이번달의 퇴출 명단");
        ResponseEntity<Map<String, Map<String, Object>>> response = attendanceService.getPreviousMonthAttendanceStats();
    }
}
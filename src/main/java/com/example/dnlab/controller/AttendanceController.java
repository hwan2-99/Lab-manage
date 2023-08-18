package com.example.dnlab.controller;

import com.example.dnlab.domain.AttendanceStatus;
import com.example.dnlab.dto.attendance.AttendanceDto;
import com.example.dnlab.domain.Attendance;
import com.example.dnlab.service.AttendanceService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
@Slf4j
@RestController
@RequestMapping("/attendance")
@RequiredArgsConstructor
public class AttendanceController {
    private final AttendanceService attendanceService;

    @PostMapping("/doAttendance")
    @PreAuthorize("hasAnyRole('PROFESSOR', 'MANAGER', 'RESEARCHER')")
    public ResponseEntity<AttendanceDto.StartCheck> doAttendance(@RequestBody AttendanceDto.StartCheck req){
        attendanceService.doAttendance(req);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping("/monthly/all")
    @PreAuthorize("hasAnyRole('PROFESSOR', 'MANAGER')")
    public ResponseEntity<Map<String, Map<AttendanceStatus, Integer>>> getMonthlyAttendanceForAll(@RequestParam int year, @RequestParam int month) {

        ResponseEntity<Map<String, Map<AttendanceStatus, Integer>>> response;

        try {
            ResponseEntity<Map<String, Map<AttendanceStatus, Integer>>> attendanceStatsResponse = attendanceService.getUserAttendanceStats(year, month);
            Map<String, Map<AttendanceStatus, Integer>> statsMap = attendanceStatsResponse.getBody();
            Map<String, Map<AttendanceStatus, Integer>> attendanceInfoMap = new HashMap<>();

            for (Map.Entry<String, Map<AttendanceStatus, Integer>> entry : statsMap.entrySet()) {
                String userName = entry.getKey();
                Map<AttendanceStatus, Integer> userStats = entry.getValue();

                // 여기에서 userName과 userStats를 그대로 맵에 추가합니다.
                attendanceInfoMap.put(userName, userStats);
            }

            response = ResponseEntity.ok(attendanceInfoMap);
        } catch (Exception e) {
            log.info(String.valueOf(e));
            // 예외 처리
            response = ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }

        return response;
    }

    @GetMapping("/details")
    @PreAuthorize("hasAnyRole('PROFESSOR', 'MANAGER')")
    public ResponseEntity<Map<String, List<Attendance>>> getAttendanceDetails(@RequestParam int year, @RequestParam int month, @RequestParam String userName) {
        log.info("test");
        return ResponseEntity.ok(attendanceService.getAttendanceDetails(year, month, userName));
    }

}
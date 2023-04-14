package com.example.dnlab.domain.attendance.controller;

import com.example.dnlab.domain.attendance.dto.AttendanceDto;
import com.example.dnlab.domain.attendance.service.AttendanceService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;

@RestController
@RequestMapping("/attendance")
@RequiredArgsConstructor
public class AttendanceController {
    private final AttendanceService attendanceService;

    @PostMapping("/doAttendance")
    public ResponseEntity<AttendanceDto.StartCheck> doAttendance(@RequestBody AttendanceDto.StartCheck req){
        attendanceService.doAttendance(req);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }


}

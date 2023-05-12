package com.example.dnlab.domain.attendance.controller;

import com.example.dnlab.domain.attendance.dto.AttendanceDto;
import com.example.dnlab.domain.attendance.entity.Attendance;
import com.example.dnlab.domain.attendance.service.AttendanceService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.List;

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

    @GetMapping("/monthly/all")
    public List<Attendance> getMonthlyAttendanceForAll(@RequestParam int year, @RequestParam int month) {
        return attendanceService.getMonthlyAttendanceForAll(year, month);
    }


}

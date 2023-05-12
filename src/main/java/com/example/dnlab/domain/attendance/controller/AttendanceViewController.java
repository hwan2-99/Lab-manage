package com.example.dnlab.domain.attendance.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/attendance")
@Controller
public class AttendanceViewController {
    @GetMapping("/attendanceCheck")
    public String getMonthlyAttendanceForAll(){
        return "AttendanceDetail";
    }
}

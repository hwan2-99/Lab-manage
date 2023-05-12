package com.example.dnlab.domain.attendance.service;

import com.example.dnlab.domain.attendance.AttendanceStatus;
import com.example.dnlab.domain.attendance.dto.AttendanceDto;
import com.example.dnlab.domain.attendance.entity.Attendance;
import com.example.dnlab.domain.attendance.repository.AttendanceMapper;
import com.example.dnlab.domain.user.entity.User;
import com.example.dnlab.domain.user.repository.UserMapper;
import com.example.dnlab.domain.user.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class AttendanceService {
    private final AttendanceMapper attendanceMapper;
    private final UserMapper userMapper;
    private final HttpSession session;

    //출석 메소드(정상과 지각만)
    public void doAttendance(AttendanceDto.StartCheck req){
        User user = (User)session.getAttribute("user");
        int userNum = user.getNum();

        Date today = getTodayAt(0, 0);

        // 이미 출석했는지 확인 하기 위한 출석 객체 받아오기

        Attendance attendance = attendanceMapper.findAttendanceByUserNumAndStartTimeBetween(userNum, today, new Date());
        if (attendance == null) { // 출석한 적이 없는 경우에만 출석체크를 처리
            if(req.getStartTime().before(getTodayAt(10,0))){
                attendanceMapper.insertAttendance(new Attendance(userNum, AttendanceStatus.NORMAL, req.getStartTime()));
                log.info("회원: {}, 출근시간: {}",userNum,req.getStartTime());
            }else{
                attendanceMapper.insertAttendance(new Attendance(userNum,AttendanceStatus.LATE, req.getStartTime()));
                log.info("회원: {}, 출근시간: {}",userNum,req.getStartTime());
            }
        } else { // 이미 출석한 경우
            log.info("회원: {}, 이미 출석한 날짜: {}",userNum,today);
        }
    }

    // 금일의 시간 반환 메소드
    public Date getTodayAt(int hour, int minute) {
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.HOUR_OF_DAY, hour);
        cal.set(Calendar.MINUTE, minute);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MILLISECOND, 0);
        return cal.getTime();
    }

    public List<Attendance> getMonthlyAttendanceForAll(int year, int month) {
        List<Attendance> attendanceList = attendanceMapper.getMonthlyAttendanceForAll(year, month);
        for (Attendance attendance : attendanceList) {
            User user = userMapper.getUserByNum(attendance.getUserNum());
            if (user != null) {
                attendance.setUserName(user.getName());
            } else {
                attendance.setUserName("Unknown");
            }
        }
        return attendanceList;
    }


}

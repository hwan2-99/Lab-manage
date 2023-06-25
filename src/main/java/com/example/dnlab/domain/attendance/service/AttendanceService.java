package com.example.dnlab.domain.attendance.service;

import com.example.dnlab.domain.attendance.AttendanceStatus;
import com.example.dnlab.domain.attendance.dto.AttendanceDto;
import com.example.dnlab.domain.attendance.entity.Attendance;
import com.example.dnlab.domain.attendance.repository.AttendanceMapper;
import com.example.dnlab.domain.user.entity.User;
import com.example.dnlab.domain.user.repository.UserMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpSession;
import java.util.*;

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
        int user_num = user.getNum();
        System.out.println(user_num);

        Date today = getTodayAt(0, 0);

        // 이미 출석했는지 확인 하기 위한 출석 객체 받아오기

        Attendance attendance = attendanceMapper.findAttendanceByUserNumAndStartTimeBetween(user_num, today, new Date());
        if (attendance == null) { // 출석한 적이 없는 경우에만 출석체크를 처리
            if(req.getStartTime().before(getTodayAt(10,0))){
                attendanceMapper.insertAttendance(new Attendance(user_num, AttendanceStatus.NORMAL, req.getStartTime()));
                log.info("회원: {}, 출근시간: {}",user_num,req.getStartTime());
            }else{
                attendanceMapper.insertAttendance(new Attendance(user_num,AttendanceStatus.LATE, req.getStartTime()));
                log.info("회원: {}, 출근시간: {}",user_num,req.getStartTime());
            }
        } else { // 이미 출석한 경우
            log.info("회원: {}, 이미 출석한 날짜: {}",user_num,today);
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
    //월별 출석일 수 가져오기
    public ResponseEntity<Map<String, Map<AttendanceStatus, Integer>>> getUserAttendanceStats(int year, int month) {
        List<Attendance> attendanceList = attendanceMapper.getMonthlyAttendanceForAll(year, month);

        Map<String, Map<AttendanceStatus, Integer>> statsMap = new HashMap<>();

        for (Attendance attendance : attendanceList) {
            int userNum = attendance.getUser_num();
            User user = userMapper.getUserByNum(userNum);
            if (user == null) {
                // 사용자 정보가 없는 경우 스킵합니다.
                continue;
            }

            String userName = user.getName();

            AttendanceStatus status = attendance.getStatus();

            // 해당 사용자 이름으로 맵에서 출석 상태별 수를 가져옵니다.
            Map<AttendanceStatus, Integer> userStats = statsMap.get(userName);

            // 맵에 사용자 이름이 없는 경우, 새로운 맵을 생성하여 맵에 추가합니다.
            if (userStats == null) {
                userStats = new HashMap<>();
                statsMap.put(userName, userStats);
            }

            // 출석 상태별 수를 카운트합니다.
            Integer count = userStats.getOrDefault(status, 0);
            userStats.put(status, count + 1);
        }

        return ResponseEntity.ok(statsMap);
    }
    public void checkAndAddAbsence() {
        // 현재 날짜 및 시간 정보 가져오기
        Date currentDate = new Date();

        // 출석이 추가되지 않은 사용자 목록 가져오기
        List<User> usersWithoutAttendance = userMapper.getUsersWithoutAttendance();

        for (User user : usersWithoutAttendance) {
            // 사용자의 출석 상태를 결석으로 설정하고 저장합니다.
            Attendance absence = new Attendance(user.getNum(), AttendanceStatus.ABSENT, currentDate);
            attendanceMapper.insertAttendance(absence);

            log.info("회원: {}, 결석 처리 날짜: {}", user.getNum(), currentDate);
        }
    }

}

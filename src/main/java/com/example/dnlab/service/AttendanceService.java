package com.example.dnlab.service;

import com.example.dnlab.domain.AttendanceStatus;
import com.example.dnlab.domain.Attendance;
import com.example.dnlab.dto.attendance.AttendanceReqDto;
import com.example.dnlab.dto.attendance.AttendanceResDto;
import com.example.dnlab.repository.AttendanceRepository;
import com.example.dnlab.domain.User;
import com.example.dnlab.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpSession;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.YearMonth;
import java.util.*;

@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class AttendanceService {
    private final AttendanceRepository attendanceRepository;
    private final UserRepository userRepository;

    //출석 메소드(정상과 지각만)
    public AttendanceResDto doAttendance(int userId, AttendanceReqDto req) {
        User user = userRepository.findById(userId);

        Date today = getTodayAt(0, 0);
        Date endDate = getTodayAt(21, 0);

        // 이미 출석했는지 확인 하기 위한 출석 객체 받아오기
        Attendance attendance = attendanceRepository.findAttendanceByUserIdAndStartTimeBetween(userId, today, endDate);
        if (attendance == null) { // 출석한 적이 없는 경우에만 출석체크를 처리
            log.info("service:{}",req.getStartTime());
            if (req.getStartTime().before(getTodayAt(10, 0))) {
                AttendanceStatus attendanceStatus = AttendanceStatus.NORMAL;
                Attendance newAttendance = Attendance.builder()
                        .user(user)
                        .status(attendanceStatus)
                        .startTime(req.getStartTime())
                        .build();
                attendanceRepository.save(newAttendance);
                log.info("회원: {}, 출근시간: {}", userId, req.getStartTime());
                return AttendanceResDto.builder()
                        .startTime(newAttendance.getStartTime())
                        .status(newAttendance.getStatus())
                        .build();
            }else{
                AttendanceStatus attendanceStatus = AttendanceStatus.LATE;
                Attendance newAttendance = Attendance.builder()
                        .user(user)
                        .status(attendanceStatus)
                        .startTime(req.getStartTime())
                        .build();
                attendanceRepository.save(newAttendance);
                log.info("회원: {}, 출근시간: {}", userId, req.getStartTime());
                return AttendanceResDto.builder()
                        .startTime(newAttendance.getStartTime())
                        .status(newAttendance.getStatus())
                        .build();
            }
        } else {
            log.info("이미 출석한 적이 있습니다."); // 이미 출석한 경우에 대한 처리
            return AttendanceResDto.builder()
                    .startTime(attendance.getStartTime())
                    .status(attendance.getStatus())
                    .build();
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
        List<Attendance> attendanceList = attendanceRepository.getMonthlyAttendanceForAll(year, month);

        Map<String, Map<AttendanceStatus, Integer>> statsMap = new HashMap<>();

        for (Attendance attendance : attendanceList) {
            User user = userRepository.findById(attendance.getUser().getId());
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
    //결석 추가
    public void checkAndAddAbsence() {
        // 현재 날짜 및 시간 정보 가져오기
        Date currentDate = new Date();

        // 출석이 추가되지 않은 사용자 목록 가져오기
        List<User> usersWithoutAttendance = userRepository.getUsersWithoutAttendance();

        List<Attendance> absences = new ArrayList<>();

        for (User user : usersWithoutAttendance) {
            // 사용자의 출석 상태를 결석으로 설정하고 저장합니다.
            Attendance absence = Attendance.builder()
                    .startTime(currentDate)
                    .status(AttendanceStatus.ABSENT)
                    .user(user)
                    .build();
            absences.add(absence);

            log.info("회원: {}, 결석 처리 날짜: {}", user.getId(), currentDate);
        }

        attendanceRepository.saveAll(absences);
    }

    //퇴출명단 리스트
    public ResponseEntity<Map<String, Map<String, Object>>> getPreviousMonthAttendanceStats() {
        // 현재 날짜를 가져와 이전 달의 연도와 월을 계산
        LocalDate currentDate = LocalDate.now();
        YearMonth previousMonth = YearMonth.from(currentDate.minusMonths(1));
        int year = previousMonth.getYear();
        int month = previousMonth.getMonthValue();

        List<Attendance> attendanceList = attendanceRepository.getMonthlyAttendanceForAll(year, month);

        Map<String, Map<String, Object>> statsMap = new HashMap<>();
        int totalDays = previousMonth.lengthOfMonth(); // 이전 달의 총 일 수
        int weekdays = 0; // 주말을 제외한 일 수

        for (Attendance attendance : attendanceList) {
            User user = userRepository.findById(attendance.getUser().getId());
            if (user == null) {
                // 사용자 정보가 없는 경우 스킵합니다.
                continue;
            }

            String userName = user.getName();
            AttendanceStatus status = attendance.getStatus();

            // 해당 사용자 이름으로 맵에서 출석 상태별 수를 가져옵니다.
            Map<String, Object> userStats = statsMap.get(userName);

            // 맵에 사용자 이름이 없는 경우, 새로운 맵을 생성하여 맵에 추가합니다.
            if (userStats == null) {
                userStats = new HashMap<>();
                statsMap.put(userName, userStats);
            }

            // 출석 상태별 수를 카운트합니다.
            Integer count = (Integer) userStats.getOrDefault(status.toString(), 0);
            userStats.put(status.toString(), count + 1);
        }

        // 주말을 제외한 일 수를 계산합니다.
        for (int i = 1; i <= totalDays; i++) {
            LocalDate date = LocalDate.of(year, month, i);
            DayOfWeek dayOfWeek = date.getDayOfWeek();
            if (dayOfWeek != DayOfWeek.SATURDAY && dayOfWeek != DayOfWeek.SUNDAY) {
                weekdays++;
            }
        }

        // 결과를 맵에 추가하고, 정상 출석률을 계산하여 넣습니다.
        for (Map.Entry<String, Map<String, Object>> entry : statsMap.entrySet()) {
            Map<String, Object> userStats = entry.getValue();
            int normalAttendanceCount = (Integer) userStats.getOrDefault(AttendanceStatus.NORMAL.toString(), 0);
            double attendanceRate = (double) normalAttendanceCount / weekdays * 100;

            // 정상 출석률이 66보다 낮으면 결과에 추가합니다.
            if (attendanceRate < 66) {
                userStats.put("Weekdays_Count", weekdays);
                userStats.put("Attendance_Rate", attendanceRate);
                userStats.put("Low_Attendance_Rate", true);
            } else {
                // 출석률이 66 이상인 경우, 해당 정보를 제외합니다.
                statsMap.remove(entry.getKey());
            }
        }

        return ResponseEntity.ok(statsMap);
    }
    // 특정 회원 출석정보 가져오기
    public Map<String, List<Attendance>> getAttendanceDetails(int year, int month, String userName) {
        List<User> userList = userRepository.getUserByName(userName);
        if (userList .isEmpty()) {
            throw new IllegalArgumentException("유저가 없음");
        }

        int user_num = userList.get(0).getId();
        List<Attendance> attendanceList = attendanceRepository.getMonthlyAttendanceForUser(year, month, user_num);
        log.info("년도 : {}",year);
        log.info("월 : {}",month);

        Map<String, List<Attendance>> attendanceDetailsMap = new HashMap<>();

        for (Attendance attendance : attendanceList) {
            Date date = attendance.getStartTime();
            AttendanceStatus status = attendance.getStatus();

            List<Attendance> attendanceDetails = attendanceDetailsMap.get(date);

            if (attendanceDetails == null) {
                attendanceDetails = new ArrayList<>();
                attendanceDetailsMap.put(String.valueOf(date), attendanceDetails);
            }

            attendanceDetails.add(Attendance.builder()
                    .startTime(date)
                    .status(status)
                    .build());
        }

        return attendanceDetailsMap;
    }
}


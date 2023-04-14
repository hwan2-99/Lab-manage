package com.example.dnlab.domain.attendance.repository;

import com.example.dnlab.domain.attendance.entity.Attendance;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface AttendanceMapper {
    void insertAttendance(Attendance attendance); // 출석
    Attendance getLastAttendanceByUserNum(int userNum); //퇴근을 위한 마지막 출근정보 가져오기
}

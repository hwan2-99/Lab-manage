package com.example.dnlab.domain.attendance.repository;

import com.example.dnlab.domain.attendance.entity.Attendance;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;

@Mapper
@Repository
public interface AttendanceMapper {
    void insertAttendance(Attendance attendance); // 출석

    Attendance findAttendanceByUserNumAndStartTimeBetween(@Param("userNum") int userNum, @Param("startDateTime") Date startDateTime, @Param("endDateTime") Date endDateTime); // 해당 날짜에 출석한 내역이 있는지 조회


//    Attendance getLastAttendanceByUserNum(int userNum); //퇴근을 위한 마지막 출근정보 가져오기
}

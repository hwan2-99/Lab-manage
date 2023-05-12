package com.example.dnlab.domain.attendance.repository;

import com.example.dnlab.domain.attendance.entity.Attendance;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Mapper
@Repository
public interface AttendanceMapper {
    void insertAttendance(Attendance attendance); // 출석

    Attendance findAttendanceByUserNumAndStartTimeBetween(@Param("userNum") int userNum, @Param("startDateTime") Date startDateTime, @Param("endDateTime") Date endDateTime); // 해당 날짜에 출석한 내역이 있는지 조회

    List<Attendance> getMonthlyAttendanceForAll(@Param("year") int year, @Param("month") int month);
}

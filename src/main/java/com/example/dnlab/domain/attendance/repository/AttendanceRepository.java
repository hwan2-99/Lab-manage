package com.example.dnlab.domain.attendance.repository;

import com.example.dnlab.domain.attendance.entity.Attendance;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface AttendanceRepository extends JpaRepository<Attendance, Integer> {

    @Query("SELECT a FROM Attendance a WHERE a.user.num = :userNum AND a.startTime >= :startDate AND a.startTime <= :endDate")
    Attendance findAttendanceByUserNumAndStartTimeBetween(int userNum, Date startDate, Date endDate);

    @Query("SELECT a FROM Attendance a WHERE YEAR(a.startTime) = :year AND MONTH(a.startTime) = :month")
    List<Attendance> getMonthlyAttendanceForAll(int year, int month);

    @Query("SELECT a FROM Attendance a WHERE YEAR(a.startTime) = :year AND MONTH(a.startTime) = :month AND a.user.num = :userNum")
    List<Attendance> getMonthlyAttendanceForUser(int year, int month, int userNum);
}

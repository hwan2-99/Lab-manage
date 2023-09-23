package com.example.dnlab.repository;

import com.example.dnlab.domain.Attendance;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Date;
import java.util.List;

public interface AttendanceRepository extends JpaRepository<Attendance, Integer> {

    @Query("SELECT a FROM Attendance a WHERE a.user.id = :id AND a.startTime >= :startDate AND a.startTime <= :endDate")
    Attendance findAttendanceByUserIdAndStartTimeBetween(int id, Date startDate, Date endDate);

    @Query("SELECT a FROM Attendance a WHERE YEAR(a.startTime) = :year AND MONTH(a.startTime) = :month")
    List<Attendance> getMonthlyAttendanceForAll(int year, int month);

    @Query("SELECT a FROM Attendance a WHERE YEAR(a.startTime) = :year AND MONTH(a.startTime) = :month AND a.user.id = :id")
    List<Attendance> getMonthlyAttendanceForUser(int year, int month, int id);
}

package com.example.dnlab.repository;

import com.example.dnlab.domain.Application;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;


public interface ApplicationRepository extends JpaRepository<Application,Integer> {
    // pk로 신청서 가져오기
    Application findById(int id);

    // 신청서 조회(교수님 전용)
    @Query("SELECT a FROM Application a ORDER BY a.createdAt DESC")
    List<Application> findAllDesc(Pageable pageable);

    // 신청서 상세
    @Query("SELECT a FROM Application a WHERE a.id = :id")
    Application getApplicationDetail(@Param("id") int id);

    // 신청서 승인
    @Modifying
    @Query("UPDATE Application a SET a.status = 'approved' WHERE a.id = :id")
    void accessApplication(@Param("id") int id);

    // 신청서 거절
    @Modifying
    @Query("UPDATE Application a SET a.status = 'REJECTED' WHERE a.id = :id")
    void rejectApplicationByNum(@Param("id") int id);


}

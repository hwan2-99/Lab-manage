package com.example.dnlab.domain.application.repository;

import com.example.dnlab.domain.application.entity.Application;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;


public interface ApplicationRepository extends JpaRepository<Application,Integer> {
    // pk로 신청서 가져오기
    Application getApplicationByNum(int num);

    // 신청서 조회(교수님 전용)
    @Query("SELECT a FROM Application a")
    List<Application> getAllApplicationsWithUserInfo();

    // 신청서 상세
    @Query("SELECT a FROM Application a WHERE a.num = :num")
    Application getApplicationDetail(@Param("num") int num);

    // 신청서 승인
    @Modifying
    @Query("UPDATE Application a SET a.status = 'approved' WHERE a.num = :num")
    void accessApplication(@Param("num") int num);

    // 신청서 거절
    @Modifying
    @Query("UPDATE Application a SET a.status = 'REJECTED' WHERE a.num = :num")
    void rejectApplicationByNum(@Param("num") int num);


}

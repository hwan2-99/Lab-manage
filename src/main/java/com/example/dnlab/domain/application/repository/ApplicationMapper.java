package com.example.dnlab.domain.application.repository;

import com.example.dnlab.domain.application.dto.ApplicationDto;
import com.example.dnlab.domain.application.entity.Application;
import com.example.dnlab.domain.user.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface ApplicationMapper {

    //신청서 작성
    void createApplication(Application application);

    //pk로 신청서 가져오기
    Application getApplicationByNum(int num);
    //신청서 조회(교수님 전용)
    List<Application> getAllApplication();

    //신청서 조회(교수님 전용)
    List<Application> getAllApplicationsWithUserInfo();

    // 신청서 상세
    Application getApplicationDetail(int num);

    // 신청서 승인
    void accessApplication(int num);

    // 신청서 거절
    void rejectApplication(int num);

}

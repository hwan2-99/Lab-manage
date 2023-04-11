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

    //신청서 조회(교수님 전용)
    List<Application> getAllApplication();



    ApplicationDto.LabSignUpReq getApplicationById(Long id);
}

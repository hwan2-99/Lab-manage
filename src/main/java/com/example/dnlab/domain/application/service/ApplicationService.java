package com.example.dnlab.domain.application.service;

import com.example.dnlab.domain.application.dto.ApplicationDto;
import com.example.dnlab.domain.application.entity.Application;
import com.example.dnlab.domain.application.repository.ApplicationMapper;
import com.example.dnlab.domain.user.entity.User;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpSession;
import java.util.List;

@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class ApplicationService {

    private final ApplicationMapper applicationMapper;
    private final HttpSession session;


    //신청서 작성
    public void crateApplication(ApplicationDto.LabSignUpReq req){

        User user = (User)session.getAttribute("user");
        int user_num = user.getNum();
        log.info("지원 동기: {}, 자기소개 : {}, 원하는 연구활동 : {} , 유저 pk : {}",req.getMotive(),req.getMotive(),req.getWanted(),user.getNum());
        applicationMapper.createApplication(new Application(req.getMotive(), req.getIntro(), req.getWanted(), user_num));
    }

    //모든 신청서 조회
    public List<Application> getAllApplications(){
        return applicationMapper.getAllApplication();
    }

}

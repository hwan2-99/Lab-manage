package com.example.dnlab.domain.application.service;

import com.example.dnlab.domain.application.dto.ApplicationDto;
import com.example.dnlab.domain.application.entity.Application;
import com.example.dnlab.domain.application.repository.ApplicationMapper;
import com.example.dnlab.domain.user.entity.User;
import com.example.dnlab.domain.user.repository.UserMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpSession;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class ApplicationService {

    private final ApplicationMapper applicationMapper;
    private final UserMapper userMapper;
    private final HttpSession session;
    LocalDate today = LocalDate.now();
    LocalDateTime now = LocalDateTime.now();
    int year = now.getYear();
    int generation = year-2003;

    //신청서 작성
    public void crateApplication(ApplicationDto.LabSignUpReq req){

        User user = (User)session.getAttribute("user");
        int user_num = user.getNum();
        log.info("지원 동기: {}, 자기소개 : {}, 원하는 연구활동 : {} , 유저 pk : {}",req.getMotive(),req.getMotive(),req.getWanted(),user.getNum());
        applicationMapper.createApplication(new Application(req.getMotive(), req.getIntro(), req.getWanted(), user_num, today));
    }

    //모든 신청서 조회
    public List<Application> getAllApplications(){
        return applicationMapper.getAllApplicationsWithUserInfo();
    }

    public Application getApplicationDetail(int num){
        return applicationMapper.getApplicationDetail(num);
    }

    public void approveApplication(int num){

        Application application = applicationMapper.getApplicationByNum(num);
        applicationMapper.accessApplication(application.getNum());

        User user = userMapper.getUserByNum(application.getUser_num());
        int user_num = user.getNum();

        userMapper.updateUserGeneration(generation, user_num,false);

    }

    public void rejectApplication(int num){

        Application application = applicationMapper.getApplicationByNum(num);
        applicationMapper.rejectApplication(application.getNum());

    }


}

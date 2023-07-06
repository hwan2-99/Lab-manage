package com.example.dnlab.domain.application.service;

import com.example.dnlab.domain.application.dto.ApplicationDto;
import com.example.dnlab.domain.application.entity.Application;
import com.example.dnlab.domain.application.entity.ApplicationStatus;
import com.example.dnlab.domain.application.repository.ApplicationRepository;
import com.example.dnlab.domain.user.entity.User;
import com.example.dnlab.domain.user.repository.UserRepository;
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

    private final ApplicationRepository applicationRepository;
    private final UserRepository userRepository;
    private final HttpSession session;
    LocalDate today = LocalDate.now();
    LocalDateTime now = LocalDateTime.now();
    int year = now.getYear();
    int generation = year-2003;

    //신청서 작성
    public int createApplication(ApplicationDto.LabSignUpReq req) {
        User user = (User) session.getAttribute("user");
        log.info("지원 동기: {}, 자기소개: {}, 원하는 연구활동: {}, 유저 pk: {}", req.getMotive(), req.getIntro(), req.getWanted(), user.getNum());
        Application application = Application.builder()
                .motive(req.getMotive())
                .intro(req.getIntro())
                .wanted(req.getWanted())
                .createdAt(LocalDate.now())
                .status(ApplicationStatus.PENDING)
                .user(user)
                .build();
        return applicationRepository.save(application).getNum();
    }

    //모든 신청서 조회
    public List<Application> getAllApplications(){
        return applicationRepository.getAllApplicationsWithUserInfo();
    }

    public Application getApplicationDetail(int num){
        return applicationRepository.getApplicationDetail(num);
    }

    public void approveApplication(int num){

        Application application = applicationRepository.getApplicationByNum(num);
        applicationRepository.accessApplication(application.getNum());

        User user = userRepository.getUserByNum(application.getUser().getNum());
        int user_num = user.getNum();

        userRepository.updateUserGeneration(generation, user_num,false);

    }

    public void rejectApplication(int num){

        Application application = applicationRepository.getApplicationByNum(num);
        applicationRepository.rejectApplicationByNum(application.getNum());

    }


}

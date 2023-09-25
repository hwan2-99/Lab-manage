package com.example.dnlab.service;

import com.example.dnlab.domain.Application;
import com.example.dnlab.domain.ApplicationStatus;
import com.example.dnlab.dto.application.ApplicationListResDto;
import com.example.dnlab.dto.application.ApplicationResDto;
import com.example.dnlab.dto.application.ApplicationSignUpReqDto;
import com.example.dnlab.repository.ApplicationRepository;
import com.example.dnlab.domain.Role;
import com.example.dnlab.domain.User;
import com.example.dnlab.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class ApplicationService {

    private final ApplicationRepository applicationRepository;
    private final UserRepository userRepository;
    LocalDate today = LocalDate.now();
    LocalDateTime now = LocalDateTime.now();
    int year = now.getYear();
    int generation = year-2003;

    //신청서 작성
    public ApplicationResDto createApplication(int userId, ApplicationSignUpReqDto req) {
        User user = userRepository.findById(userId);
        Application application = req.toEntity(user);
        log.info("지원 동기: {}, 자기소개: {}, 원하는 연구활동: {}, 유저 pk: {}", req.getMotive(), req.getIntro(), req.getWanted(), user.getId());
        application.setCreatedAt(today);

        applicationRepository.save(application);

        return ApplicationResDto.builder()
                .userId(user.getId())
                .status(application.getStatus())
                .build();
    }

    //모든 신청서 조회
    public List<ApplicationListResDto> getAllApplications(Pageable pageable){
        List<Application> applicationList = applicationRepository.findAllDesc(pageable);


        return applicationList.stream()
                .map(ApplicationListResDto::new)
                .collect(Collectors.toList());
    }

    public Application getApplicationDetail(int id){
        return applicationRepository.getApplicationDetail(id);
    }

    public void approveApplication(int id) {
        Application application = applicationRepository.findById(id);
        applicationRepository.accessApplication(application.getId());

        User user = userRepository.findById(application.getUser().getId());
        int userNum = user.getId();

        // 기존 권한 유지하고 RESEARCHER 권한 추가
        user.setRole(Role.RESEARCHER);
        userRepository.save(user);
    }

    public void rejectApplication(int id){

        Application application = applicationRepository.findById(id);
        applicationRepository.rejectApplicationByNum(application.getId());

    }


}

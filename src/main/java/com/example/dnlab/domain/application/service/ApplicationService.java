package com.example.dnlab.domain.application.service;

import com.example.dnlab.domain.application.dto.ApplicationDto;
import com.example.dnlab.domain.application.entity.Application;
import com.example.dnlab.domain.application.repository.ApplicationMapper;
import com.example.dnlab.domain.user.entity.User;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.web.ServerProperties;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpSession;

@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class ApplicationService {

    private final ApplicationMapper applicationMapper;
    HttpSession session;


    public void crateApplication(ApplicationDto.LabSignUpReq req){

        User user = (User)session.getAttribute("user");
        applicationMapper.createApplication(new Application(req.getMotive(), req.getIntro(), req.getWanted(), user.getNum()));
    }

}

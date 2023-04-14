package com.example.dnlab.domain.application.controller;

import com.example.dnlab.domain.application.dto.ApplicationDto;
import com.example.dnlab.domain.application.entity.Application;
import com.example.dnlab.domain.application.service.ApplicationService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/application")
@RequiredArgsConstructor
public class ApplicationController {

    @Autowired
    private final ApplicationService applicationService;

    // 신청서 작성 Post
    @PostMapping("/insertApplication")
    public ResponseEntity<Void> insertApplication(@RequestBody ApplicationDto.LabSignUpReq req){
        applicationService.crateApplication(req);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    //신청서 조회 Get
    @GetMapping("/applications")
    public List<Application> getAllApplications(){
        return applicationService.getAllApplications();
    }
}

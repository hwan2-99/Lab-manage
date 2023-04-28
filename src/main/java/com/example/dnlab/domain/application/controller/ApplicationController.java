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
import java.util.Map;

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

    @GetMapping("/detail/{num}")
    public Application getApplicationDetail(@PathVariable("num") int num) {
        return applicationService.getApplicationDetail(num);
    }

    @PostMapping("/action")
    public ResponseEntity<String> approveAction(@RequestBody Map<String, Object> request) {
        int userNum = Integer.parseInt(request.get("user_num").toString());
        String action = request.get("action").toString();

            if (action.equals("approve")) {
                applicationService.approveApplication(userNum);
            } else if (action.equals("reject")) {
            applicationService.rejectApplication(userNum);
            } else {
                return new ResponseEntity<>("Invalid action", HttpStatus.BAD_REQUEST);
            }
            return new ResponseEntity<>("Application action succeeded", HttpStatus.OK);
        }
    }









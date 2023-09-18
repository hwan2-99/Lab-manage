package com.example.dnlab.controller;

import com.example.dnlab.domain.Application;
import com.example.dnlab.dto.application.LabSignUpReq;
import com.example.dnlab.service.ApplicationService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
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
    @PreAuthorize("hasAnyRole('MEMBER')")
    public ResponseEntity<Void> insertApplication(@RequestBody LabSignUpReq req){
        applicationService.createApplication(req);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    //신청서 조회 Get
    @GetMapping("/applications")
    @PreAuthorize("hasAnyRole('PROFESSOR', 'MANAGER')")
    public List<Application> getAllApplications(){
        return applicationService.getAllApplications();
    }

    @GetMapping("/detail/{id}")
    @PreAuthorize("hasAnyRole('PROFESSOR', 'MANAGER')")
    public Application getApplicationDetail(@PathVariable("id") int id) {
        return applicationService.getApplicationDetail(id);
    }

    @PostMapping("/action")
    @PreAuthorize("hasAnyRole('PROFESSOR')")
    public ResponseEntity<String> approveAction(@RequestBody Map<String, Object> request) {
        int userNum = Integer.parseInt(request.get("user_id").toString());
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









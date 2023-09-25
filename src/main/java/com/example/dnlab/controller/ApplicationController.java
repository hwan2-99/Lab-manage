package com.example.dnlab.controller;

import com.example.dnlab.domain.Application;
import com.example.dnlab.domain.auth.PrincipalDetails;
import com.example.dnlab.dto.application.ApplicationListResDto;
import com.example.dnlab.dto.application.ApplicationResDto;
import com.example.dnlab.dto.application.ApplicationSignUpReqDto;
import com.example.dnlab.service.ApplicationService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
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
    public ResponseEntity<ApplicationResDto> insertApplication(@AuthenticationPrincipal PrincipalDetails principalDetails, @RequestBody ApplicationSignUpReqDto req){
        int userId = principalDetails.getId();
        ApplicationResDto res = applicationService.createApplication(userId,req);
        return ResponseEntity.ok(res);
    }

    //신청서 조회 Get
    @GetMapping("/applications")
    public ResponseEntity<List<ApplicationListResDto>> findAllApplications(){
        Pageable pageable = PageRequest.of(0, 10);

        return ResponseEntity.ok(applicationService.getAllApplications(pageable));
    }

    @GetMapping("/detail/{id}")
    public Application getApplicationDetail(@PathVariable("id") int id) {
        return applicationService.getApplicationDetail(id);
    }

    @PostMapping("/action")
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









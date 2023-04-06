package com.example.dnlab.domain.application.controller;

import com.example.dnlab.domain.application.dto.ApplicationDto;
import com.example.dnlab.domain.application.service.ApplicationService;
import com.example.dnlab.domain.book.dto.BookDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/application")
@RequiredArgsConstructor
public class ApplicationController {
    private final ApplicationService applicationService;
    @PostMapping("/insertApplication")
    public ResponseEntity<Void> insertApplication(@RequestBody ApplicationDto.LabSignUpReq req){
        applicationService.crateApplication(req);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }
}

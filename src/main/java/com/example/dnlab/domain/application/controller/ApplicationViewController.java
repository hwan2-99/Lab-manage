package com.example.dnlab.domain.application.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/application")
public class ApplicationViewController {

    @GetMapping("insertApplication")
    public String insertApplication(){
        return "insertApplication";
    }
    @GetMapping("AllApplication")
    public String getAllApplication(){
        return "applications";
    }
}
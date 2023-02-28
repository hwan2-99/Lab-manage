package com.example.dnlab.controller;

import com.example.dnlab.dto.UserDto;
import com.example.dnlab.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpSession;

@Slf4j
@Controller
@RequiredArgsConstructor
public class UserController {
    public final Logger logger = LoggerFactory.getLogger(UserController.class);
    private final UserService service;

    @PostMapping("/getId")
    public String getId(UserDto userDto){
        logger.info("UserController getId()");
        boolean a = service.getId(userDto);
        if(a) {
            return "no";
        }
        return "ok";
    }

    @GetMapping("/addUser")
    public String addUserPage(){
        System.out.printf("test");
        return "signUp";
    }

    @PostMapping("/addUser")
    public String addUser(UserDto.SignUpReq req, HttpSession session){
        logger.info("UserController addUser()");

        service.addUser(req);
        session.setAttribute("id",req.getId());
        return "login";
    }
    @GetMapping("/login")
    public String login(){
        logger.info("UserController login()");
        return "login";
    }

    @PostMapping("/login")
    public UserDto login(UserDto userDto){
        logger.info("UserController login()");
        return service.login(userDto);
    }
}

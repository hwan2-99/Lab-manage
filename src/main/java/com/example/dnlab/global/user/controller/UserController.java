package com.example.dnlab.global.user.controller;

import com.example.dnlab.global.user.dto.UserDto;
import com.example.dnlab.global.user.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;

@Slf4j
@RestController
@RequiredArgsConstructor
public class UserController {
    private final UserService service;

    // 회원가입 페이지 이동
    @GetMapping("/addUser")
    public String addUserPage(){
        System.out.printf("test");
        return "signUp";
    }

    //로그인 Get
    @GetMapping("/login")
    public String login(){
        return "login";
    }

    //로그아웃
    @GetMapping("/logOut")
    public ResponseEntity logOut(HttpSession session){
        session.invalidate();
        return new ResponseEntity(HttpStatus.OK);
    }

    // 회원가입 요청 처리 및 페이지 이동
    @PostMapping("/addUser")
    public ResponseEntity addUser(@RequestBody UserDto.SignUpReq req){
        service.addUser(req);
        return new ResponseEntity<>(HttpStatus.OK);

    }
    //로그인 Post
    @PostMapping("/login")
    public ResponseEntity<UserDto.UserCheckId>addUser(HttpSession session, @RequestBody UserDto.loginReq req){
        UserDto.UserCheckId userCheckId = service.login(req);

        session.setAttribute("id",userCheckId.getId());

        return new ResponseEntity<>(userCheckId, HttpStatus.OK);
    }
}

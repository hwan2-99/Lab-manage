package com.example.dnlab.controller;

import com.example.dnlab.dto.UserDto;
import com.example.dnlab.entity.User;
import com.example.dnlab.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import javax.servlet.http.HttpSession;

@Slf4j
@Controller
@RequiredArgsConstructor
public class UserController {
    public final Logger logger = LoggerFactory.getLogger(UserController.class);
    private final UserService service;

    // 회원가입 페이지 이동
    @GetMapping("/addUser")
    public String addUserPage(){
        System.out.printf("test");
        return "signUp";
    }

    // 회원가입 요청 처리 및 페이지 이동
    @PostMapping("/addUser")
    public String addUser(UserDto.SignUpReq req, UserDto.SignUpRes res){
        logger.info("UserController addUser()");

        if(req.getId() == ""){
            res.setErrorMsg("Id를 입력 해주세요.");
            return "signUp";
        }else if(req.getName() == ""){
            res.setErrorMsg("이름을 입력 해주세요.");
            return "signUp";
        }else if(req.getPw() == ""){
            res.setErrorMsg("패스워드를 입력 해주세요.");
            return "signUp";
        }

        if(service.duplicationCheck(req)){
            res.setErrorMsg("이미 존재하는 아이디입니다.");
            return "signUp";
        }else{
            service.addUser(req);
            return "home";
        }

    }
    //로그인 Get
    @GetMapping("/login")
    public String login(){
        logger.info("UserController login()");
        return "login";
    }

    //로그인 Post
    @PostMapping("/login")
    public String login(HttpSession session, UserDto.loginReq req, Model model){
        if(session.getAttribute("login")!= null){
            session.removeAttribute("login");
        }

        User user = service.login(req);

        if(user != null){
            session.setAttribute("login",user);
            logger.info("로그인 성공");
            return "redirect:/home";
        }
        logger.info("로그인 실패");
        return "redirect:/login";
    }

}

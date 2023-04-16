package com.example.dnlab.domain.user.controller;

import com.example.dnlab.domain.user.dto.UserDto;
import com.example.dnlab.domain.user.entity.User;
import com.example.dnlab.domain.user.service.UserService;
import lombok.RequiredArgsConstructor;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.List;
@Slf4j
@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    //로그아웃
    @PostMapping("/logOut")
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/";
    }

    //회원 가입
    @PostMapping("/signUp")
    public ResponseEntity<Void> signUp(@RequestBody UserDto.SignUpReq req){
        userService.addUser(req);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    //로그인
    @PostMapping("/login")
    public ResponseEntity<UserDto.UserCheckId> login(@RequestBody UserDto.loginReq req,HttpSession session){
        return userService.login(req,session);
    }

    //유저조회
    @GetMapping("/userList")
    public List<User>getAllUsers(){
        return userService.getAllUsers();
    }

    @GetMapping("/myPage")
    public ResponseEntity<User> getMyPage(HttpSession session) {
        User user = (User)session.getAttribute("user");
        User findUser = userService.getUserByNum(user.getNum());
        log.info("1 = {}, 2 = {} 3 = {}",findUser.getName(),findUser.getStudentId(),findUser.getGeneration());

        return ResponseEntity.ok().body(findUser);
    }

}



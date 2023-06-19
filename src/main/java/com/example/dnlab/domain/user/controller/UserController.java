package com.example.dnlab.domain.user.controller;

import com.example.dnlab.domain.user.dto.UserDto;
import com.example.dnlab.domain.user.entity.User;
import com.example.dnlab.domain.user.service.UserService;
import lombok.RequiredArgsConstructor;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.NoSuchElementException;

@Slf4j
@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    //로그아웃
    @PostMapping("/logOut")
    public void logout(HttpSession session, HttpServletResponse response) throws IOException {
        session.invalidate();
        log.info("logOut");

        // 리다이렉트할 URL
        String redirectUrl = "/";

        // RedirectView를 생성하여 리다이렉트 설정
        RedirectView redirectView = new RedirectView(redirectUrl);
        redirectView.setStatusCode(HttpStatus.SEE_OTHER);

        // HttpServletResponse를 사용하여 리다이렉트 수행
        response.sendRedirect(redirectView.getUrl());
    }

    //회원 가입
    @PostMapping("/signUp")
    public ResponseEntity<Void> signUp(@RequestBody UserDto.SignUpReq req){
        userService.addUser(req);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    // 로그인
    @PostMapping("/login")
    public ResponseEntity<Void> login(@RequestBody UserDto.loginReq req, HttpSession session) {
        return userService.login(req, session);
    }


    @GetMapping("/userList")
    public ResponseEntity<List<User>> getAllUsers() {
        List<User> users = userService.getAllUsers();
        return ResponseEntity.ok(users);
    }

    @GetMapping("/search")
    public ResponseEntity<List<User>> searchUsers(@RequestParam("name") String name) {
        try {
            List<User> userList = userService.getUserByName(name);

            if (userList.isEmpty()) {
                return ResponseEntity.noContent().build();
            } else {
                Collections.sort(userList, Comparator.comparing(User::getGeneration)); // Generation을 기준으로 오름차순 정렬
                return ResponseEntity.ok(userList);
            }
        } catch (NoSuchElementException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/myPage")
    public ResponseEntity<User> getMyPage(HttpSession session) {
        User user = (User)session.getAttribute("user");
        User findUser = userService.getUserByNum(user.getNum());
        log.info("1 = {}, 2 = {} 3 = {}",findUser.getName(),findUser.getStudentId(),findUser.getGeneration());

        return ResponseEntity.ok().body(findUser);
    }

}



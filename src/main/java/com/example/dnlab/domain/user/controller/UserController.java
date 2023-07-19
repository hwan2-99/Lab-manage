package com.example.dnlab.domain.user.controller;

import com.example.dnlab.domain.user.dto.LoginReq;
import com.example.dnlab.domain.user.dto.SearchReq;
import com.example.dnlab.domain.user.dto.SignUpReq;
import com.example.dnlab.domain.user.dto.UserDto;
import com.example.dnlab.domain.user.entity.User;
import com.example.dnlab.domain.user.service.UserService;
import lombok.RequiredArgsConstructor;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
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
    @PreAuthorize("hasAnyRole('PROFESSOR', 'MANAGER', 'RESEARCHER', 'MEMBER')")
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
    public ResponseEntity<Void> signUp(@RequestBody SignUpReq req){
        userService.addUser(req);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    // 로그인
    @PostMapping("/login")
    public ResponseEntity<Void> login(@RequestBody LoginReq req, HttpSession session) {
        log.info("con");
        return userService.login(req, session);
    }

    @GetMapping("/userList")
    @PreAuthorize("hasAnyRole('PROFESSOR', 'MANAGER', 'RESEARCHER', 'MEMBER')")
    public ResponseEntity<List<User>> getAllUsers() {
        List<User> users = userService.getAllUsers();
        return ResponseEntity.ok(users);
    }

    // 회원검색
    @GetMapping("/search")
    @PreAuthorize("hasAnyRole('PROFESSOR', 'MANAGER', 'RESEARCHER', 'MEMBER')")
    public ResponseEntity<List<User>> searchUsers(@RequestParam("name") SearchReq req) {
        try {
            List<User> userList = userService.getUserByName(req);

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
    @PreAuthorize("hasAnyRole('PROFESSOR', 'MANAGER', 'RESEARCHER')")
    public ResponseEntity<User> getMyPage(HttpSession session) {
        User user = (User)session.getAttribute("user");
        User findUser = userService.getUserByNum(user.getNum());
        log.info("1 = {}, 2 = {} 3 = {}",findUser.getName(),findUser.getStudentId(),findUser.getGeneration());

        return ResponseEntity.ok().body(findUser);
    }

}



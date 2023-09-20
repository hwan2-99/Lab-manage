package com.example.dnlab.controller;

import com.example.dnlab.dto.user.*;
import com.example.dnlab.domain.User;
import com.example.dnlab.service.UserService;
import lombok.RequiredArgsConstructor;

import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.io.IOException;
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
    public ResponseEntity<UserResDto> signUp(@Valid @RequestBody SignUpReqDto req){
        UserResDto res = userService.join(req);
        return ResponseEntity.ok(res);
    }


    // 로그인
    @PostMapping("/login")
    public ResponseEntity<LoginTokenResDto> login(@Valid @RequestBody LoginReqDto req) {
        LoginTokenResDto response = userService.login(req);
        return ResponseEntity.ok(response);
    }

    //회원 조회
    @GetMapping("/userList")
    @PreAuthorize("hasAnyRole('PROFESSOR', 'MANAGER', 'RESEARCHER')")
    public ResponseEntity<List<User>> getAllUsers() {
        List<User> users = userService.getAllUsers();
        return ResponseEntity.ok(users);
    }

    // 회원검색
    @GetMapping("/search")
    public ResponseEntity<UserPaginationResDto> searchUsers(@RequestBody SearchReqDto req, @RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "10") int size) {
        Pageable pageable = PageRequest.of(page,size);
        try {
            UserPaginationResDto userPaginationResDto = userService.getUserByName(req,pageable);

            if (userPaginationResDto == null) {
                return ResponseEntity.noContent().build();
            } else {
                return ResponseEntity.ok(userPaginationResDto);
            }
        } catch (NoSuchElementException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/myPage")
    @PreAuthorize("hasAnyRole('PROFESSOR', 'MANAGER', 'RESEARCHER')")
    public ResponseEntity<UserResDto> getMyPage(HttpSession session) {
        User user = (User)session.getAttribute("user");
        UserResDto res = userService.getUserByNum(user.getId());

        return ResponseEntity.ok().body(res);
    }

}



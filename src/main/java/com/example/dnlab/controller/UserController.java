package com.example.dnlab.controller;

import com.example.dnlab.domain.auth.PrincipalDetails;
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
import org.springframework.security.core.annotation.AuthenticationPrincipal;
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

    //로그아웃  --- > 차 후 레디스 사용 처리
    @PostMapping("/logout")
    public void logout(@AuthenticationPrincipal PrincipalDetails principalDetails) throws IOException {
        log.info("logOut");

        String uid = principalDetails.getUsername();

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
    public ResponseEntity<UserResDto> getMyPage(@AuthenticationPrincipal PrincipalDetails principalDetails) {

        int userId = principalDetails.getId();
        UserResDto res = userService.getById(userId);

        return ResponseEntity.ok().body(res);
    }

}



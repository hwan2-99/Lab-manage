package com.example.dnlab.domain.user.service;

import com.example.dnlab.domain.user.dto.LoginReq;
import com.example.dnlab.domain.user.dto.SearchReq;
import com.example.dnlab.domain.user.dto.SignUpReq;
import com.example.dnlab.domain.user.dto.UserDto;
import com.example.dnlab.domain.user.entity.User;
import com.example.dnlab.domain.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Collections;
import java.util.Comparator;
import java.util.NoSuchElementException;

@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    //회원 가입
    public void addUser(SignUpReq req){
        log.info("name : {}, StudentId :{}, id = {}, pw = {} ",req.getName(), req.getStudentId(),req.getId(), req.getPw());

        if(userRepository.getUserById(req.getId()) != null) {
            throw new IllegalArgumentException("이미 존재하는 학번입니다.");
        }
        userRepository.save(User.builder()
                .name(req.getName())
                .studentId(req.getStudentId())
                .pw(req.getPw())
                .id(req.getId())
                .build());
    }

    // 로그인
    public ResponseEntity<Void> login(LoginReq req, HttpSession session) {

        User user = userRepository.findById(req.getId());
        log.info("id: {}, pw: {}", req.getId(), req.getPw());
        log.info("userId: {}, userPw: {}", user.getId(), user.getPw());

        // 아이디 확인
        if (user != null) {
            // 비밀번호 비교
            if (user.getPw().equals(req.getPw())) {
                // 세션 저장(pk)
                session.setAttribute("user", user);

                // 세션 id 반환
                return ResponseEntity.ok().header("SESSION-ID", session.getId()).build();
            }
        }
        // 로그인 실패
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
    }

    //전체 회원 조회
    public List<User> getAllUsers() {
        List<User> userList = userRepository.findAll();

        if (userList.isEmpty()) {
            throw new NoSuchElementException("찾는 연구생 없음.");
        }else{
            Collections.sort(userList, Comparator.comparing(User::getGeneration)); // Generation을 기준으로 오름차순 정렬
        }
        return userList;
    }

    //이름으로 회원 조회
    public List<User> getUserByName(SearchReq req) {
        List<User> userList = userRepository.getUserByName(req.getName());

        if (userList.isEmpty()) {
            throw new NoSuchElementException("찾는 연구생 없음.");
        }else{
            Collections.sort(userList, Comparator.comparing(User::getGeneration)); // Generation을 기준으로 오름차순 정렬
        }
        return userList;
    }

    //회원 한명의 정보 받아오기
    public User getUserByNum(int num){
        return userRepository.getUserByNum(num);
    }
}

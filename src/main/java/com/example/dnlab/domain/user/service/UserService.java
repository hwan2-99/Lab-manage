package com.example.dnlab.domain.user.service;

import com.example.dnlab.domain.user.dto.UserDto;
import com.example.dnlab.domain.user.entity.User;
import com.example.dnlab.domain.user.repository.UserMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpSession;
import java.util.List;

@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class UserService {

    private final UserMapper userMapper;

    //회원 가입
    public void addUser(UserDto.SignUpReq req){
        log.info("name : {}, StudentId :{}, id = {}, pw = {} ",req.getName(), req.getStudentId(),req.getId(), req.getPw());

        if(userMapper.getUserById(req.getId()) != null) {
            throw new IllegalArgumentException("이미 존재하는 학번입니다.");
        }
        userMapper.insertUser(new User(req.getName(),req.getStudentId(), req.getPw(),req.getId()));
    }


    //로그인
    public ResponseEntity<UserDto.UserCheckId> login(UserDto.loginReq req, HttpSession session){
        log.info("id : {}",req.getId());

        User user = userMapper.selectUserById(req.getId());



        //존재하는 아이디가 아닐때
        if(user == null){
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }

        //비밀번호 틀림
        if(user.getPw() != req.getPw()){
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }

        //로그인성공
        UserDto.UserCheckId userCheckId = new UserDto.UserCheckId();
        userCheckId.setId(user.getId());

        //세션 저장(pk)
        session.setAttribute("user",user);

        //세션 id 반환
        return ResponseEntity.ok().header("SESSION-ID", session.getId()).body(userCheckId);
    }

    public List<User> getAllUsers(){
        return userMapper.getAllUser();
    }

}

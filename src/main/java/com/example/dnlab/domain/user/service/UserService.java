package com.example.dnlab.domain.user.service;

import com.example.dnlab.domain.user.dto.UserDto;
import com.example.dnlab.domain.user.repository.UserMapper;
import com.example.dnlab.domain.user.entity.User;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class UserService {

    @Autowired
    private UserMapper userMapper;

    //회원 가입
    public void addUser(UserDto.SignUpReq req){
        log.info("name : {}, id = {}, pw = {} ",req.getName(), req.getId(), req.getPw());
        userMapper.insertUser(new User(req.getName(), req.getId(),null, req.getPw(),false));
    }


    //로그인
    public UserDto.UserCheckId login(UserDto.loginReq req){
        log.info("id : {}, pw : {}",req.getId(),req.getPw());

        User user = userMapper.login(req);

        UserDto.UserCheckId userCheckId = new UserDto.UserCheckId();

        try{
            userCheckId.setId(user.getId());
        }catch (Exception e){
            log.info("error");
        }
        return userCheckId;
    }
}

package com.example.dnlab.service;

import com.example.dnlab.dao.UserDao;
import com.example.dnlab.dao.UserMapper;
import com.example.dnlab.dto.UserDto;
import com.example.dnlab.entity.User;
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

    private final UserDao userDao;
    @Autowired
    private UserMapper userMapper;

    //회원 id 중복검사
    public boolean duplicationCheck(UserDto.SignUpReq req){
        User user[] = userMapper.selectUserById((new User(req.getId())));
        if(user.length == 0){
            return false;
        }else{
            return true;
        }
    }

    //회원 가입
    public void addUser(UserDto.SignUpReq req){
        userMapper.insertUser(new User(req.getName(), req.getId(), req.getPw()));
    }

    public User getUserById(String id){
        User user = userMapper.getUserById(id);
        return user;
    }

    public User login(UserDto.loginReq req){
        System.out.println(req.getPw());
        User user = userMapper.getUserById(req.getId());
        System.out.println("id:"+user.getId());
        System.out.println("id:"+req.getId());
        System.out.println("pw:"+req.getPw());
        System.out.println("pw:"+req.getPw());


        if(req.getPw().equals(user.getPw())){
            return user;
        }else{
            return null;
        }
    }
}

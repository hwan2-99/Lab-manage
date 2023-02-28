package com.example.dnlab.service;

import com.example.dnlab.dao.UserDao;
import com.example.dnlab.dao.UserMapper;
import com.example.dnlab.dto.UserDto;
import com.example.dnlab.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class UserService {

    private final UserDao userDao;
    @Autowired
    private UserMapper userMapper;

    //회원 id 중복검사
    public boolean getId(UserDto userDto){
        int a = userDao.getId(userDto);
        return a > 0;
    }

    public void addUser(UserDto.SignUpReq req){
        userMapper.insertUser(new User(req.getName(), req.getId(), req.getPw()));

    }

    public UserDto login(UserDto userDto){
        return userDao.login(userDto);
    }
}

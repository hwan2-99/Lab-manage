package com.example.dnlab.global.user.repository;

import com.example.dnlab.global.user.dto.UserDto;
import com.example.dnlab.global.user.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface UserMapper {
    User login(UserDto.loginReq req);
    User dupleId(UserDto.SignUpReq req);
    public void insertUser(User user);
}

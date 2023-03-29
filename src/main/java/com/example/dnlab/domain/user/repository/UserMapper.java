package com.example.dnlab.domain.user.repository;

import com.example.dnlab.domain.user.dto.UserDto;
import com.example.dnlab.domain.user.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface UserMapper {
    User login(UserDto.loginReq req);
    User dupleId(UserDto.SignUpReq req);
    public void insertUser(User user);
}

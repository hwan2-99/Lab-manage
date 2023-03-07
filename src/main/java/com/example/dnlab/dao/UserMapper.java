package com.example.dnlab.dao;

import com.example.dnlab.dto.UserDto;
import com.example.dnlab.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface UserMapper {
    User getUserById(String id);
    public void insertUser(User user);
    public User[] selectUserById(User user);
}

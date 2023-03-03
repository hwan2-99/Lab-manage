package com.example.dnlab.dao;

import com.example.dnlab.entity.User;
import org.apache.ibatis.annotations.Mapper;

import java.util.HashMap;
@Mapper
public interface UserMapper {
    public void insertUser(User user);
    public User[] selectUserById(User user);
}

package com.example.dnlab.domain.user.repository;

import com.example.dnlab.domain.user.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface UserMapper {
    User login(User user);
    User selectUserById(String id);
    void insertUser(User user);
}

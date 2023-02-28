package com.example.dnlab.dao;

import com.example.dnlab.dto.UserDto;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface UserDao {
    int getId(UserDto userDto);
    int addUser(UserDto userDto);
    UserDto login(UserDto userDto);
}

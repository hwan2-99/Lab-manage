package com.example.dnlab.domain.user.repository;

import com.example.dnlab.domain.user.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface UserMapper {

    User getUserById(String id); // 중복 회원 확인용 아이디 가져오기
    User selectUserById(String id);
    void insertUser(User user);

    List<User> getAllUser(); // 학생 조회

}
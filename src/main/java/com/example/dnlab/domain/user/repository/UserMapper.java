package com.example.dnlab.domain.user.repository;

import com.example.dnlab.domain.user.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface UserMapper {

    User getUserById(String id); // 중복 회원 확인용 아이디 가져오기
    User selectUserById(String id); // 로그인
    void insertUser(User user); // 회원가입
    List<User> getAllUser(); // 연구생 전원 조회
    List<User> getUserByName(String name); // 연구생 이름으로 조회
    User getUserByNum(int num); // 중복 회원 확인용 아이디 가져오기
    void updateUserGeneration(int generation, int user_num, boolean leaderYN); // 학생 가입승인시 기수 주입

    List<User> getUsersWithoutAttendance(); // 출석정보가 없는 회원 리스트 가져오기

}

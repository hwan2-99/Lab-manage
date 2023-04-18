package com.example.dnlab.domain.toDo.repository;

import com.example.dnlab.domain.toDo.entity.ToDo;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
@Mapper
public interface ToDoMapper {

    void createToDo(ToDo toDo); // to-do-list 생성
    List<ToDo> viewThisWeekToDo(int user_num, LocalDate week_start_date); // 금주의 to-do-list 불러오기
    void deleteTodo(int num);  // to-do-list 삭제
    void updateContent(int num, String content);  // to-do-list 내용수정
    List<ToDo> viewThisWeekAllToDo(LocalDate week_start_date);     // 금주의 연구실생 전원의 to-do-list 불러오기

}

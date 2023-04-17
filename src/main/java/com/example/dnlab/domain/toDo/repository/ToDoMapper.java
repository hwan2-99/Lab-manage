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
    List<ToDo> viewThisWeekToDo(int user_num, LocalDate week_start_date);
    void deleteTodo(int num);
}

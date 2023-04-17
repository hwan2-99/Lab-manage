package com.example.dnlab.domain.toDo.service;

import com.example.dnlab.domain.toDo.dto.ToDoDto;
import com.example.dnlab.domain.toDo.entity.ToDo;
import com.example.dnlab.domain.toDo.repository.ToDoMapper;
import com.example.dnlab.domain.user.entity.User;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpSession;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.List;

@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class ToDoService {
    private final ToDoMapper toDoMapper;
    private final HttpSession session;
    LocalDate today = LocalDate.now();
    LocalDate monday = today.with(DayOfWeek.MONDAY);


    // to-do-list 생성
    public void createToDo(ToDoDto.createReq req){
        log.info("content : {}",req.getContent());
        log.info("월요일 날짜: {}",monday);

        User user = (User)session.getAttribute("user");
        toDoMapper.createToDo(new ToDo(req.getContent(),user.getNum(),monday));
    }

    //금주의 to-do-list 불러오기
    public List<ToDo> viewThisWeekToDo(){
        User user = (User)session.getAttribute("user");
        return toDoMapper.viewThisWeekToDo(user.getNum(),monday);
    }

    public void deleteToDo(int num){
        toDoMapper.deleteTodo(num);
    }

}

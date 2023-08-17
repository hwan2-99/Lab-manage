package com.example.dnlab.service;

import com.example.dnlab.dto.todo.TodoReqDto;
import com.example.dnlab.dto.todo.TodoUpdateReqDto;
import com.example.dnlab.domain.Todo;
import com.example.dnlab.repository.ToDoRepository;
import com.example.dnlab.domain.User;
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
    private final ToDoRepository toDoRepository;
    private final HttpSession session;
    LocalDate today = LocalDate.now();
    LocalDate monday = today.with(DayOfWeek.MONDAY);


    // to-do-list 생성
    public void createToDo(TodoReqDto req){
        log.info("content : {}",req.getContent());
        log.info("월요일 날짜: {}",monday);

        User user = (User)session.getAttribute("user");
        Todo toDo = Todo.builder()
                .content(req.getContent())
                .user(user)
                .weekStartDate(monday)
                .build();

        toDoRepository.save(toDo);
    }

    //금주의 to-do-list 불러오기
    public List<Todo> viewThisWeekToDo(){
        User user = (User)session.getAttribute("user");
        return toDoRepository.findByUserNumAndWeekStartDate(user.getNum(),monday);
    }

    // to-do-list 삭제
    public void deleteToDo(int num){
        toDoRepository.deleteByNum(num);
    }

    //to-do-list 내용 수정
    public void updateContent(int num, TodoUpdateReqDto req){
        log.info("content : {}",req.getContent());

        toDoRepository.updateContentByNum(num, req.getContent());
    }
    // 금주의 연구실생 전원의 to-do-list 불러오기
    public List<Todo> viewThisWeekAllToDo(){
        return toDoRepository.findAllByWeekStartDate(monday);
    }
}

package com.example.dnlab.service;

import com.example.dnlab.dto.todo.TodoCreateReqDto;
import com.example.dnlab.dto.todo.TodoDeleteResDto;
import com.example.dnlab.dto.todo.TodoListResDto;
import com.example.dnlab.dto.todo.TodoResDto;
import com.example.dnlab.domain.Todo;
import com.example.dnlab.repository.ToDoRepository;
import com.example.dnlab.domain.User;
import com.example.dnlab.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpSession;
import org.springframework.data.domain.Pageable;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class ToDoService {
    private final ToDoRepository toDoRepository;
    private final UserRepository userRepository;
    LocalDate today = LocalDate.now();
    LocalDate monday = today.with(DayOfWeek.MONDAY);


    // to-do-list 생성
    public TodoResDto createToDo(TodoCreateReqDto req,int userId){
        log.info("content : {}",req.getContent());
        log.info("월요일 날짜: {}",monday);
        User user = userRepository.findById(userId);
        Todo todo = req.toEntity(user);

        todo.setWeekStartDate(monday);
        toDoRepository.save(todo);
        return TodoResDto.builder()
                .content(todo.getContent())
                .createdAt(todo.getCreatedAt())
                .build();
    }

    //금주의 to-do-list 불러오기
    public List<TodoListResDto> viewThisWeekToDo(int userId,Pageable pageable){
        List<Todo> todoList = toDoRepository.findByUserIdAndWeekStartDate(userId,monday,pageable);
        return todoList.stream()
                .map(TodoListResDto::new)
                .collect(Collectors.toList());
    }

    // to-do-list 삭제
    public TodoDeleteResDto deleteToDo(int id){
        toDoRepository.deleteById(id);
        return TodoDeleteResDto.builder()
                .id(id)
                .build();
    }

    //to-do-list 내용 수정
    public TodoResDto updateContent(int id, TodoCreateReqDto req){

        toDoRepository.updateContentById(id, req.getContent());
        return TodoResDto.builder()
                .id(id)
                .content(req.getContent())
                .build();
    }
    // 금주의 연구실생 전원의 to-do-list 불러오기
    public List<TodoListResDto> viewThisWeekAllToDo(Pageable pageable){
        List<Todo> todoList = toDoRepository.findAllByWeekStartDate(monday,pageable);
        return todoList.stream()
                .map(TodoListResDto::new)
                .collect(Collectors.toList());
    }
}

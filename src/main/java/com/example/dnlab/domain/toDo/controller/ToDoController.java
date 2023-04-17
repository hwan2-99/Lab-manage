package com.example.dnlab.domain.toDo.controller;

import com.example.dnlab.domain.toDo.dto.ToDoDto;
import com.example.dnlab.domain.toDo.entity.ToDo;
import com.example.dnlab.domain.toDo.service.ToDoService;
import com.example.dnlab.domain.user.dto.UserDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/todo")
@RequiredArgsConstructor
public class ToDoController {
    private final ToDoService todoservice;
    @PostMapping("/createTodo")
    public ResponseEntity<Void> createTodo(@RequestBody ToDoDto.createReq req){
        todoservice.createToDo(req);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }
    @GetMapping("/thisWeekTodo")
    public List<ToDo> viewThisWeekTodo(){
        return todoservice.viewThisWeekToDo();
    }
    @DeleteMapping("/delete/{num}")
    public ResponseEntity<Void> deleteTodoById(@PathVariable int num) {
        todoservice.deleteToDo(num);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}

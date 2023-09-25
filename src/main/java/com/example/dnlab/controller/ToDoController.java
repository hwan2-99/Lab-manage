package com.example.dnlab.controller;

import com.example.dnlab.domain.auth.PrincipalDetails;
import com.example.dnlab.dto.todo.TodoCreateReqDto;
import com.example.dnlab.dto.todo.TodoListResDto;
import com.example.dnlab.dto.todo.TodoResDto;
import com.example.dnlab.domain.Todo;
import com.example.dnlab.service.ToDoService;
import com.example.dnlab.domain.User;
import com.example.dnlab.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/todo")
@RequiredArgsConstructor
public class ToDoController {
    private final ToDoService todoservice;
    private final UserRepository userRepository;

    @PostMapping("/createTodo")
    public ResponseEntity<TodoResDto> createTodo(@AuthenticationPrincipal PrincipalDetails principalDetails, @RequestBody TodoCreateReqDto req){
        int userId = principalDetails.getId();
        TodoResDto res = todoservice.createToDo(req,userId);
        return ResponseEntity.ok(res);
    }
    @GetMapping("/myTodo")
    public ResponseEntity<List<TodoListResDto>> myTodo(@AuthenticationPrincipal PrincipalDetails principalDetails){
        Pageable pageable = PageRequest.of(0, 10);
        int userId = principalDetails.getId();
        return ResponseEntity.ok(todoservice.viewThisWeekToDo(userId,pageable));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteTodoById(@PathVariable int id) {
        todoservice.deleteToDo(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PutMapping("/updateContent/{id}")
    public ResponseEntity<Void> updateContent(@PathVariable int id, @RequestBody TodoCreateReqDto req) {
        todoservice.updateContent(id, req);
        log.info("수정대상:{} , 수정내용: {}",id,req.getContent());
        return new ResponseEntity<>(HttpStatus.OK);
    }
    @GetMapping("/thisWeekAllTodo")
    public ResponseEntity<List<TodoListResDto>> viewThisWeekAllTodoByUser() {
        Pageable pageable = PageRequest.of(0, 10);

        return ResponseEntity.ok(todoservice.viewThisWeekAllToDo(pageable));
    }

}

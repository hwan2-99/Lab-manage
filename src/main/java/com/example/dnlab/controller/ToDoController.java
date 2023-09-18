package com.example.dnlab.controller;

import com.example.dnlab.dto.todo.TodoReqDto;
import com.example.dnlab.dto.todo.TodoUpdateReqDto;
import com.example.dnlab.domain.Todo;
import com.example.dnlab.service.ToDoService;
import com.example.dnlab.domain.User;
import com.example.dnlab.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@RestController
@RequestMapping("/todo")
@RequiredArgsConstructor
public class ToDoController {
    private final ToDoService todoservice;
    private final UserRepository userRepository;

    @PostMapping("/createTodo")
    @PreAuthorize("hasAnyRole('PROFESSOR', 'MANAGER', 'RESEARCHER')")
    public ResponseEntity<Void> createTodo(@RequestBody TodoReqDto req){
        todoservice.createToDo(req);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }
    @GetMapping("/thisWeekTodo")
    @PreAuthorize("hasAnyRole('PROFESSOR', 'MANAGER', 'RESEARCHER')")
    public List<Todo> viewThisWeekTodo(){
        return todoservice.viewThisWeekToDo();
    }

    @DeleteMapping("/delete/{num}")
    @PreAuthorize("hasAnyRole('PROFESSOR', 'MANAGER', 'RESEARCHER')")
    public ResponseEntity<Void> deleteTodoById(@PathVariable int num) {
        todoservice.deleteToDo(num);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PutMapping("/updateContent/{num}")
    @PreAuthorize("hasAnyRole('PROFESSOR', 'MANAGER', 'RESEARCHER')")
    public ResponseEntity<Void> updateContent(@PathVariable int num, @RequestBody TodoUpdateReqDto req) {
        todoservice.updateContent(num, req);
        log.info("수정대상:{} , 수정내용: {}",num,req.getContent());
        return new ResponseEntity<>(HttpStatus.OK);
    }
    @GetMapping("/thisWeekAllTodo")
    @PreAuthorize("hasAnyRole('PROFESSOR', 'MANAGER', 'RESEARCHER')")
    public Map<String, List<Todo>> viewThisWeekAllTodoByUser() {
        List<Todo> allTodo = todoservice.viewThisWeekAllToDo();       // to-do-list 전부 가져오기
        Map<String, List<Todo>> todoByUser = new HashMap<>();         // 유저별 to-do-list 분류를 위한 hashMap

        // todo 객체 리스트를 조회하여 유저별로 todo 객체를 묶는 로직
        for (Todo todo : allTodo) {
            User user = userRepository.findById(todo.getUser().getId());
            if (user != null) { // 유저 객체가 null일 경우 무시
                String userName = user.getName();
                List<Todo> userTodo = todoByUser.getOrDefault(userName, new ArrayList<>());
                userTodo.add(todo);
                todoByUser.put(userName, userTodo);
            }
        }
        return todoByUser;
    }

}

package com.example.dnlab.domain.toDo.controller;

import com.example.dnlab.domain.toDo.dto.ToDoDto;
import com.example.dnlab.domain.toDo.entity.ToDo;
import com.example.dnlab.domain.toDo.service.ToDoService;
import com.example.dnlab.domain.user.dto.UserDto;
import com.example.dnlab.domain.user.entity.User;
import com.example.dnlab.domain.user.repository.UserMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    private final UserMapper userMapper;

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

    @PutMapping("/updateContent/{num}")
    public ResponseEntity<Void> updateContent(@PathVariable int num, @RequestBody ToDoDto.updateReq req) {
        todoservice.updateContent(num, req);
        log.info("수정대상:{} , 수정내용: {}",num,req.getContent());
        return new ResponseEntity<>(HttpStatus.OK);
    }
    @GetMapping("/thisWeekAllTodo")
    public Map<String, List<ToDo>> viewThisWeekAllTodoByUser() {
        List<ToDo> allTodo = todoservice.viewThisWeekAllToDo();       // to-do-list 전부 가져오기
        Map<String, List<ToDo>> todoByUser = new HashMap<>();         // 유저별 to-do-list 분류를 위한 hashMap

        // todo 객체 리스트를 조회하여 유저별로 todo 객체를 묶는 로직
        for (ToDo todo : allTodo) {
            User user = userMapper.getUserByNum(todo.getUser_num());
            if (user != null) { // 유저 객체가 null일 경우 무시
                String userName = user.getName();
                List<ToDo> userTodo = todoByUser.getOrDefault(userName, new ArrayList<>());
                userTodo.add(todo);
                todoByUser.put(userName, userTodo);
            }
        }
        return todoByUser;
    }

}
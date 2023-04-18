package com.example.dnlab.domain.toDo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/todo")
@Controller
public class ToDoViewController {

    @GetMapping("/thisWeekTodos")
    public String userLists(){
        return "thisWeekTodos";
    }
}

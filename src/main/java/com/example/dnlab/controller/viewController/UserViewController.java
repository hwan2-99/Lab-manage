package com.example.dnlab.controller.viewController;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/user")
@Controller
public class UserViewController {
    @GetMapping("login")
    public String login(){
        return "login";
    }
    @GetMapping("signUp")
    public String signUp(){
        return "signUp";
    }
    @GetMapping("/userLists")
    public String userLists(){
        return "userLists";
    }
    @GetMapping("/myPages")
    public String myPages(){
        return "myPages";
    }
}

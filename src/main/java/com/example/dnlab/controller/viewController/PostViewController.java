package com.example.dnlab.controller.viewController;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/post")
@Controller
public class PostViewController {
    @GetMapping("createPost")
    public String createPost(){
        return "createPost";
    }

}

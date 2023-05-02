package com.example.dnlab.domain.post.controller;

import com.example.dnlab.domain.post.entity.Board;
import com.example.dnlab.domain.post.repository.BoardMapper;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@RequestMapping("/post")
@Controller
public class PostViewController {
    @GetMapping("createPost")
    public String createPost(){
        return "createPost";
    }

}

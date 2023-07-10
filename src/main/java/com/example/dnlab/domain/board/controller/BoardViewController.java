package com.example.dnlab.domain.board.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/board")
@Controller
public class BoardViewController {
    @GetMapping("/{board_num}/posts")
    public String getBoardPosts(){
        return "board";
    }

}

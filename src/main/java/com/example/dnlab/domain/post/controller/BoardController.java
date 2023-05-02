package com.example.dnlab.domain.post.controller;

import com.example.dnlab.domain.post.entity.Board;
import com.example.dnlab.domain.post.service.BoardService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/post")
@RequiredArgsConstructor
public class BoardController {
    private final BoardService boardService;

    @GetMapping("/boardLists")
    public List<Board> getBoardList(){
        return boardService.getBoardList();
    }
}
package com.example.dnlab.domain.board.controller;

import com.example.dnlab.domain.board.entity.Board;
import com.example.dnlab.domain.post.entity.Post;
import com.example.dnlab.domain.board.service.BoardService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/board")
@RequiredArgsConstructor
public class BoardController {
    private final BoardService boardService;

    @GetMapping("/boardLists")
    public List<Board> getBoardList(){
        return boardService.getBoardList();
    }


}

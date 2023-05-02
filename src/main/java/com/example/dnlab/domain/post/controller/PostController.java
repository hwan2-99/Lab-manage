package com.example.dnlab.domain.post.controller;

import com.example.dnlab.domain.post.dto.PostDto;
import com.example.dnlab.domain.post.entity.Board;
import com.example.dnlab.domain.post.service.BoardService;
import com.example.dnlab.domain.post.service.PostService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.List;


@Slf4j
@RestController
@RequestMapping("/post")
@RequiredArgsConstructor
public class PostController {
    private final PostService postService;
    private final BoardService boardService;

    @PostMapping("/createPost")
    public void createPost(@RequestBody PostDto.postReq req, @RequestParam("board_num") int board_num) {

        postService.createPost(req, board_num);
    }
    @GetMapping("/boardList")
    public List<Board> getBoardList() {
        return boardService.getBoardList();
    }
}

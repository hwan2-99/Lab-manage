package com.example.dnlab.controller;

import com.example.dnlab.service.BoardService;
import com.example.dnlab.dto.post.PostReqDto;
import com.example.dnlab.dto.post.PostUpdateReqDto;
import com.example.dnlab.domain.Post;
import com.example.dnlab.service.PostService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@Slf4j
@RestController
@RequestMapping("/post")
@RequiredArgsConstructor
public class PostController {
    private final PostService postService;
    private final BoardService boardService;

    @PostMapping("/{boardNum}")
    @PreAuthorize("hasAnyRole('PROFESSOR', 'MANAGER', 'RESEARCHER')")
    public ResponseEntity<Void> createPost(@RequestBody PostReqDto req, @PathVariable int boardNum) {
        postService.createPost(req, boardNum);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{num}")
    @PreAuthorize("hasAnyRole('PROFESSOR', 'MANAGER', 'RESEARCHER')")
    public ResponseEntity<Void> deletePost(@PathVariable int num) {
        postService.deletePost(num);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/{num}")
    @PreAuthorize("hasAnyRole('PROFESSOR', 'MANAGER', 'RESEARCHER')")
    public ResponseEntity<Void> updateContent(@PathVariable int num, @RequestBody PostUpdateReqDto req) {
        postService.updateContent(num, req);
        return ResponseEntity.ok().build();
    }

    @GetMapping
    public ResponseEntity<List<Post>> getAllPosts() {
        List<Post> posts = postService.getAllPost();
        return ResponseEntity.ok(posts);
    }

    @GetMapping("/board/{boardNum}")
    public ResponseEntity<List<Post>> getPostsByBoardNum(@PathVariable int boardNum) {
        List<Post> posts = postService.getPostByBoardNum(boardNum);
        return ResponseEntity.ok(posts);
    }

}

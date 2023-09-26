package com.example.dnlab.controller;

import com.example.dnlab.domain.auth.PrincipalDetails;
import com.example.dnlab.dto.post.PostListResDto;
import com.example.dnlab.dto.post.PostResDto;
import com.example.dnlab.service.BoardService;
import com.example.dnlab.dto.post.PostReqDto;
import com.example.dnlab.dto.post.PostUpdateReqDto;
import com.example.dnlab.domain.Post;
import com.example.dnlab.service.PostService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@Slf4j
@RestController
@RequestMapping("/post")
@RequiredArgsConstructor
public class PostController {
    private final PostService postService;
    private final BoardService boardService;

    @PostMapping("/createPost")
    public ResponseEntity<PostResDto> createPost(@AuthenticationPrincipal PrincipalDetails principalDetails, @RequestBody PostReqDto req, @RequestParam int boardId) {
        int userId = principalDetails.getId();
        PostResDto res = postService.createPost(req, boardId,userId);
        return ResponseEntity.ok(res);
    }

    @DeleteMapping("/{num}")
    public ResponseEntity<Void> deletePost(@PathVariable int num) {
        postService.deletePost(num);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/{num}")
    public ResponseEntity<Void> updateContent(@PathVariable int num, @RequestBody PostUpdateReqDto req) {
        postService.updateContent(num, req);
        return ResponseEntity.ok().build();
    }

    @GetMapping
    public ResponseEntity<List<Post>> getAllPosts() {
        List<Post> posts = postService.getAllPost();
        return ResponseEntity.ok(posts);
    }

    @GetMapping("/board/{boardId}")
    public ResponseEntity<List<PostListResDto>> getPostsByBoardId(@PathVariable int boardId) {
        Pageable pageable = PageRequest.of(0, 10);
        return ResponseEntity.ok(postService.getPostByBoardId(boardId,pageable));
    }

}

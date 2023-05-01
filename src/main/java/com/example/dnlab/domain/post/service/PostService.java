package com.example.dnlab.domain.post.service;

import com.example.dnlab.domain.application.entity.Application;
import com.example.dnlab.domain.post.dto.PostDto;
import com.example.dnlab.domain.post.entity.Board;
import com.example.dnlab.domain.post.entity.Post;
import com.example.dnlab.domain.post.repository.BoardMapper;
import com.example.dnlab.domain.post.repository.PostMapper;
import com.example.dnlab.domain.toDo.dto.ToDoDto;
import com.example.dnlab.domain.user.entity.User;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpSession;
import java.time.LocalDate;
import java.util.List;

@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class PostService {
    private final HttpSession session;
    LocalDate today = LocalDate.now();
    private final PostMapper postMapper;
    private final BoardMapper boardMapper;

    // 게시글 작성
    public void createPost(PostDto.postReq req,int board_num){
        User user = (User)session.getAttribute("user");
        int user_num = user.getNum();
        Board board = boardMapper.getBoardByNum(board_num);

        postMapper.createPost(new Post(board.getNum(),req.getTitle(), req.getContent(), user_num, today));

    }
    // 게시글 삭제
    public void deletePost(int num){
        postMapper.deletePost(num);
    }

    //게시글 내용 수정
    public void updateContent(int num, PostDto.updateReq req){
        log.info("content : {}",req.getContent());

        postMapper.updateContent(num, req.getContent());
    }
    // 모든 게시글 조히
    public List<Post> getAllPost(){
        return postMapper.getAllPost();
    }

    public List<Post> getPostByBoardNum(int board_num){



        return postMapper.getPostByBoardNum(board_num);
    }
}

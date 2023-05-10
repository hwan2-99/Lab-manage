package com.example.dnlab.domain.post.service;

import com.example.dnlab.domain.post.entity.Board;
import com.example.dnlab.domain.post.entity.Post;
import com.example.dnlab.domain.post.repository.BoardMapper;
import com.example.dnlab.domain.post.repository.PostMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class BoardService {

    private final BoardMapper boardMapper;
    private final PostMapper postMapper;

    // 게시글 작성에 필요한 게시판 목록 조히
    public List<Board> getBoardList(){
        return boardMapper.getBoardList();
    }

    public List<Post> getPostNyBoardNum(int board_num){
        log.info("게시판 번호:{}",board_num);
        List<Post> postList = postMapper.getPostByBoardNum(board_num);
        return postList;
    }
}

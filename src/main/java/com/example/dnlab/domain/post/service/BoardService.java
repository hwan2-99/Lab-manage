package com.example.dnlab.domain.post.service;

import com.example.dnlab.domain.post.entity.Board;
import com.example.dnlab.domain.post.entity.Post;
import com.example.dnlab.domain.post.repository.BoardMapper;
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

    // 게시글 작성에 필요한 게시판 목록 조히
    public List<Board> getBoardList(){
        return boardMapper.getBoardList();
    }
}

package com.example.dnlab.domain.post.service;

import com.example.dnlab.domain.post.entity.Board;
import com.example.dnlab.domain.post.repository.BoardMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class BoardService {

    private final BoardMapper boardMapper;

    public Board getBoardByNum(int num){
        return boardMapper.getBoardByNum(num);
    }
}

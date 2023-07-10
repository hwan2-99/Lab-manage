package com.example.dnlab.domain.board.repository;

import com.example.dnlab.domain.board.entity.Board;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface BoardRepository {
    List<Board> getBoardList();
    Board getBoardByNum(int num);
}

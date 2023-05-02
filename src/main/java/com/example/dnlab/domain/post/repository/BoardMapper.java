package com.example.dnlab.domain.post.repository;

import com.example.dnlab.domain.post.entity.Board;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface BoardMapper {
    List<Board> getBoardList();
    Board getBoardByNum(int num);
}

package com.example.dnlab.domain.post.repository;

import com.example.dnlab.domain.post.entity.Board;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface BoardMapper {
    Board getBoardByNum(int num);
}

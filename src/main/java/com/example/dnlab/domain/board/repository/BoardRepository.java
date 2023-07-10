package com.example.dnlab.domain.board.repository;

import com.example.dnlab.domain.board.entity.Board;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

public interface BoardRepository extends JpaRepository<Board, Integer> {
    Board findByNum(int num);
}

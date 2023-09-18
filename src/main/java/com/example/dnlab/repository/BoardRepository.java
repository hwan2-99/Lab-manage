package com.example.dnlab.repository;

import com.example.dnlab.domain.Board;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BoardRepository extends JpaRepository<Board, Integer> {
    Board findById(int id);
}

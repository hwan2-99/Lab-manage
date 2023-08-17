package com.example.dnlab.repository;

import com.example.dnlab.domain.Post;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PostRepository extends JpaRepository<Post, Integer> {

    // 게시판 번호에 따라 게시글 불러오기
    List<Post> findAllByBoardNum(int boardNum);
}

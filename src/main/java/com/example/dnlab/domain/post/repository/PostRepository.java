package com.example.dnlab.domain.post.repository;

import com.example.dnlab.domain.post.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PostRepository extends JpaRepository<Post, Integer> {

    // 게시판 번호에 따라 게시글 불러오기
    List<Post> findAllByBoardNum(int boardNum);
}

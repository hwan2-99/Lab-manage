package com.example.dnlab.repository;

import com.example.dnlab.domain.Post;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface PostRepository extends JpaRepository<Post, Integer> {

    // 게시판 번호에 따라 게시글 불러오기
    List<Post> findAllByBoardIdOrderByCreatedAtDesc(int boardId, Pageable pageable);

    @Modifying
    @Query("UPDATE Post p SET p.content = :content WHERE p.id = :id")
    void updateContentById(int id, String content);
}

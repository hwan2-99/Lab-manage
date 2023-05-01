package com.example.dnlab.domain.post.repository;

import com.example.dnlab.domain.post.entity.Post;
import com.example.dnlab.domain.toDo.entity.ToDo;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Mapper
@Repository
public interface PostMapper {
    void createPost(Post post); // 게시글 생성
    void deletePost(int num);  // 게시글 삭제
    void updateContent(int num, String content);  // 게시글 내용수정

    List<Post> getAllPost(); //게시글 불러오기

    List<Post> getPostByBoardNum(int board_num); // 게시판 목록별로 게시글 불러오기
}

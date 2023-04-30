package com.example.dnlab.domain.post.repository;

import com.example.dnlab.domain.application.entity.Application;
import com.example.dnlab.domain.post.entity.Post;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface PostMapper {
    void createPost(Post post);
}

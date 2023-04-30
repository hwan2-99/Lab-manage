package com.example.dnlab.domain.post.service;

import com.example.dnlab.domain.application.entity.Application;
import com.example.dnlab.domain.post.dto.PostDto;
import com.example.dnlab.domain.post.entity.Post;
import com.example.dnlab.domain.post.repository.PostMapper;
import com.example.dnlab.domain.user.entity.User;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpSession;
import java.time.LocalDate;

@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class PostService {
    private final HttpSession session;
    LocalDate today = LocalDate.now();
    private final PostMapper postMapper;
    public void createPost(PostDto.postReq req){
        User user = (User)session.getAttribute("user");
        int user_num = user.getNum();

        postMapper.createPost(new Post(req.getTitle(), req.getContent(), user_num, today));

    }

}

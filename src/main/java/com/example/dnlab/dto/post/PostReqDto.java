package com.example.dnlab.dto.post;

import com.example.dnlab.domain.Board;
import com.example.dnlab.domain.Post;
import com.example.dnlab.domain.User;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class PostReqDto {
    private String title;
    private String content;

    public Post toEntity(User user, Board board){
        return Post.builder()
                .user(user)
                .board(board)
                .title(title)
                .content(content)
                .build();

    }

}

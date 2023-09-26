package com.example.dnlab.dto.post;

import com.example.dnlab.domain.Post;
import lombok.Getter;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
public class PostListResDto {
    private int id;
    private String title;
    private String content;
    private LocalDate createdAt;

    private String userName;

    public PostListResDto(Post entity) {
        this.id = entity.getId();
        this.title = entity.getTitle();
        this.content = entity.getContent();
        this.createdAt = entity.getCreatedAt();
        this.userName = entity.getUser().getName();
    }


}

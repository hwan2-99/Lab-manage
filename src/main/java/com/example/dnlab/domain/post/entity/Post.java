package com.example.dnlab.domain.post.entity;

import com.example.dnlab.domain.user.entity.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Post {

    private int id;
    private int board_num;
    private int user_num;
    private String title;
    private String content;
    private LocalDate createdAt;
    private LocalDateTime updatedAt;


    public Post(int board_num, String title, String content, int user_num, LocalDate today) {
        this.board_num = board_num;
        this.title = title;
        this.content = content;
        this.user_num = user_num;
        this.createdAt = today;
    }
}

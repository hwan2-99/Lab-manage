package com.example.dnlab.domain.post.entity;

import com.example.dnlab.domain.board.entity.Board;
import com.example.dnlab.domain.user.entity.User;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Builder
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "num")
    private int num;
    @Column
    private String title;
    @Column
    private String content;
    @Column
    private LocalDateTime  createdAt;
    @Column
    private LocalDateTime updatedAt;

    @ManyToOne
    @JoinColumn(name = "user_num")
    private User user;

    @ManyToOne
    @JoinColumn(name = "board_num")
    private Board board;
}

package com.example.dnlab.domain;

import com.example.dnlab.domain.Board;
import com.example.dnlab.domain.User;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
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
    @JsonIgnore
    private User user;

    @ManyToOne
    @JoinColumn(name = "board_num")
    @JsonIgnore
    private Board board;
}

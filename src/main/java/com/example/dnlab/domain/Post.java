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
    @Column(name = "id")
    private int id;
    @Column
    private String title;
    @Column
    private String content;
    @Column
    private LocalDateTime  createdAt;
    @Column
    private LocalDateTime updatedAt;

    @ManyToOne
    @JoinColumn(name = "user_id")
    @JsonIgnore
    private User user;

    @ManyToOne
    @JoinColumn(name = "board_id")
    @JsonIgnore
    private Board board;
}

package com.example.dnlab.domain.board.entity;

import com.example.dnlab.domain.attendance.entity.Attendance;
import com.example.dnlab.domain.post.entity.Post;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Board {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "num")
    private int num;
    @Column
    private String name;
    @Column
    private String description;

    @OneToMany(mappedBy = "board")
    private List<Post> post = new ArrayList<>();

}

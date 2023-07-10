package com.example.dnlab.domain.user.entity;

import com.example.dnlab.domain.attendance.entity.Attendance;
import com.example.dnlab.domain.post.entity.Post;
import com.example.dnlab.domain.toDo.entity.ToDo;
import com.example.dnlab.domain.using.entity.Using;
import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Builder
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "num")
    private int num;  // 회원의 pk
    @Column(length = 45)
    private String name; //이름
    @Column
    private int studentId; //학번
    @Column(length = 45)
    private String id; //아이디
    @Column(length = 45)
    private String pw; //비밀번호
    @Column
    private int generation; //기수
    @Column
    private boolean leaderYN; // 연구실장 유무

    @OneToMany(mappedBy = "user")
    private List<Attendance> attendances = new ArrayList<>();

    @OneToMany(mappedBy = "user")
    private List<ToDo> toDoList = new ArrayList<>();

    @OneToMany(mappedBy = "user")
    private List<Post> post = new ArrayList<>();
    @OneToMany(mappedBy = "user")
    private List<Using> usings = new ArrayList<>();
}

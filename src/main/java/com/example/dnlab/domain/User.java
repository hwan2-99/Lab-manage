package com.example.dnlab.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Entity
@Builder
@Data
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
    @Column(length = 100)
    private String pw; //비밀번호
    @Column
    private int generation; //기수
    @Column
    private boolean leaderYN; // 연구실장 유무
    @Column(name = "graduationYN")
    private Boolean graduationYN;
    @ElementCollection
    @Column(name = "role")
    @Enumerated(EnumType.STRING)
    private List<Role> roles = new ArrayList<>();

    @OneToMany(mappedBy = "user")
    @JsonIgnore
    private List<Attendance> attendances = new ArrayList<>();

    @OneToMany(mappedBy = "user")
    @JsonIgnore
    private List<Todo> todoList = new ArrayList<>();

    @OneToMany(mappedBy = "user")
    @JsonIgnore
    private List<Post> post = new ArrayList<>();
    @OneToMany(mappedBy = "user")
    @JsonIgnore
    private List<EquipRental> equipRental = new ArrayList<>();

    public void addRole(Role role) {
        roles.add(role);
    }

}

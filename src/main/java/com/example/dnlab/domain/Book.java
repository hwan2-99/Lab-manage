package com.example.dnlab.domain;

import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Builder
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;
    @Column
    private String title;
    @Column
    private String author;
    @Column
    private boolean borrowYN;

    @OneToMany(mappedBy = "book") // mappedBy 속성 추가
    private List<Rental> rental = new ArrayList<>();
}

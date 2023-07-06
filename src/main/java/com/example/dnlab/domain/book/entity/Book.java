package com.example.dnlab.domain.book.entity;

import lombok.*;

import javax.persistence.*;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Builder
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "num")
    private int num;
    @Column
    private String title;
    @Column
    private String author;
    @Column
    private boolean borrowYN;



}

package com.example.dnlab.domain.book.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Book {
    private int num;
    private String title;
    private String author;
    private boolean borrowYN;

    public Book(String title, String author, boolean b) {
    }
}

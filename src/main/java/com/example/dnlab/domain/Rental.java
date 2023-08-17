package com.example.dnlab.domain;

import com.example.dnlab.domain.Book;
import com.example.dnlab.domain.User;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Builder
public class Rental {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "num")
    private int num;           // 대여 pk
    @Column
    private LocalDate rent_start_date;  // 도서 대여 시작일
    @Column
    private LocalDate rent_end_date;    // 도서 반납일

    @ManyToOne
    @JoinColumn(name = "user_num")
    @JsonIgnore
    private User user;

    @ManyToOne
    @JoinColumn(name = "book_num")
    @JsonIgnore
    private Book book;
}

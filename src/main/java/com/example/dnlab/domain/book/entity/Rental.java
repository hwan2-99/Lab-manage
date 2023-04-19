package com.example.dnlab.domain.book.entity;

import lombok.*;

import java.time.LocalDate;
import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Rental {
    private int num;           // 대여 pk
    private int book_num;      // 도서 fk
    private int user_num;      // 회원 fk
    private LocalDate rent_start_date;  // 도서 대여 시작일
    private LocalDate rent_end_date;    // 도서 반납일

    public Rental(int user_num, int book_num, LocalDate rent_start_date) {
        this.user_num = user_num;
        this.book_num = book_num;
        this.rent_start_date = rent_start_date;
    }
}

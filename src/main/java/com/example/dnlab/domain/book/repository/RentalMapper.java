package com.example.dnlab.domain.book.repository;

import com.example.dnlab.domain.book.entity.Book;
import com.example.dnlab.domain.book.entity.Rental;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;

@Mapper
@Repository
public interface RentalMapper {
    void insertRentInfo(Rental rental);
    Rental selectRentalByBookNum(int user_num,int book_num);

    void updateRentInfo(int num, LocalDate rent_end_date);
}

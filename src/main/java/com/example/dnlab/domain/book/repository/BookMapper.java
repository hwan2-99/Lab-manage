package com.example.dnlab.domain.book.repository;

import com.example.dnlab.domain.book.entity.Book;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface BookMapper {
    void insertBook(Book book);  // 도서 추가
    List<Book> getAllBook();
    Book getBookByNum(int bookNum);
    void updateBorrowY(int num);
    void updateBorrowN(int num);
}

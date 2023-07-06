package com.example.dnlab.domain.book.repository;

import com.example.dnlab.domain.book.entity.Book;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

public interface BookRepository extends JpaRepository<Book,Integer> {
    @Query("SELECT b FROM Book b")
    List<Book> getAllBooks();
    @Query("SELECT b FROM Book b WHERE b.num = :bookNum")
    Book getBookByNum(@Param("bookNum") int bookNum);

    @Modifying
    @Query("UPDATE Book b SET b.borrowYN = true WHERE b.num = :num")
    void updateBorrowY(@Param("num") int num);

    @Modifying
    @Query("UPDATE Book b SET b.borrowYN = false WHERE b.num = :num")
    void updateBorrowN(@Param("num") int num);

}

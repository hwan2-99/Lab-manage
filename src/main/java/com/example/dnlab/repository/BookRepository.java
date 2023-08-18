package com.example.dnlab.repository;

import com.example.dnlab.domain.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface BookRepository extends JpaRepository<Book,Integer> {
    @Query("SELECT b FROM Book b WHERE b.num = :bookNum")
    Book getBookByNum(@Param("bookNum") int bookNum);

    @Modifying
    @Query("UPDATE Book b SET b.borrowYN = true WHERE b.num = :num")
    void updateBorrowY(@Param("num") int num);

    @Modifying
    @Query("UPDATE Book b SET b.borrowYN = false WHERE b.num = :num")
    void updateBorrowN(@Param("num") int num);
}
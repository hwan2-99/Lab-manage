package com.example.dnlab.repository;

import com.example.dnlab.domain.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface BookRepository extends JpaRepository<Book,Integer> {
    @Query("SELECT b FROM Book b WHERE b.id = :id")
    Book findById(@Param("id") int id);

    @Modifying
    @Query("UPDATE Book b SET b.borrowYN = true WHERE b.id = :id")
    void updateBorrowY(@Param("id") int id);

    @Modifying
    @Query("UPDATE Book b SET b.borrowYN = false WHERE b.id = :id")
    void updateBorrowN(@Param("id") int id);
}

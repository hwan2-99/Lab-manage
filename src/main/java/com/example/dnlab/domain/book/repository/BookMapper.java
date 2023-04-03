package com.example.dnlab.domain.book.repository;

import com.example.dnlab.domain.book.entity.Book;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface BookMapper {
    void insertBook(Book book);
}

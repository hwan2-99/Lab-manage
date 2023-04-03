package com.example.dnlab.domain.book.service;

import com.example.dnlab.domain.book.dto.BookDto;
import com.example.dnlab.domain.book.entity.Book;
import com.example.dnlab.domain.book.repository.BookMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class BookService {

    private BookMapper bookMapper;

    public void insertBook(BookDto.insertBookReq req) {
        bookMapper.insertBook(new Book(req.getTitle(), req.getAuthor(), false));
    }
}

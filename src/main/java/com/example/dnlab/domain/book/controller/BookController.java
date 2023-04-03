package com.example.dnlab.domain.book.controller;

import com.example.dnlab.domain.book.dto.BookDto;
import com.example.dnlab.domain.book.service.BookService;
import com.example.dnlab.domain.user.dto.UserDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/book")
@RequiredArgsConstructor
public class BookController {

    private final BookService bookService;

    //도서 추가
    @PostMapping("/insertBook")
    public ResponseEntity<Void> signUp(@RequestBody BookDto.insertBookReq req){
        bookService.insertBook(req);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }
}

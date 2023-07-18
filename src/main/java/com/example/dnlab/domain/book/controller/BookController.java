package com.example.dnlab.domain.book.controller;

import com.example.dnlab.domain.book.dto.InsertBookReqDto;
import com.example.dnlab.domain.book.entity.Book;
import com.example.dnlab.domain.book.service.BookService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/book")
@RequiredArgsConstructor
public class BookController {

    private final BookService bookService;

    //도서 추가
    @PostMapping("/insertBook")
    public ResponseEntity<Void> insertBook(@RequestBody InsertBookReqDto req){
        bookService.insertBook(req);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping("/getAllBook")
    public List<Book>getAllBook(){
        return bookService.getAllBook();
    }

    @PostMapping("/borrow/{bookNum}")
    public void borrowBook(@PathVariable int bookNum) {
        bookService.borrowBook(bookNum);
    }

    @PostMapping("/return/{bookNum}")
    public ResponseEntity<String> returnBook(@PathVariable int bookNum) {
        bookService.returnBook(bookNum);
        return ResponseEntity.ok("책 반납이 완료되었습니다.");
    }
}

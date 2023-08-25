package com.example.dnlab.controller;

import com.example.dnlab.domain.User;
import com.example.dnlab.dto.book.BookResDto;
import com.example.dnlab.dto.book.InsertBookReqDto;
import com.example.dnlab.domain.Book;
import com.example.dnlab.service.BookService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.List;

@RestController
@RequestMapping("/book")
@RequiredArgsConstructor
public class BookController {

    private final BookService bookService;

    //도서 추가
    @PostMapping("/insertBook")
    @PreAuthorize("hasAnyRole('PROFESSOR', 'MANAGER')")
    public ResponseEntity<BookResDto> insertBook(@RequestBody InsertBookReqDto req){
        BookResDto res = bookService.insertBook(req);
        return ResponseEntity.ok(res);
    }

    @GetMapping("/getAllBook")
    @PreAuthorize("hasAnyRole('PROFESSOR', 'MANAGER', 'RESEARCHER')")
    public List<Book>getAllBook(){
        return bookService.getAllBook();
    }

    @PostMapping("/borrow/{bookNum}")
    @PreAuthorize("hasAnyRole('PROFESSOR', 'MANAGER', 'RESEARCHER')")
    public void borrowBook(@PathVariable int bookNum) {
        bookService.borrowBook(bookNum);
    }

    @PostMapping("/return/{bookNum}")
    @PreAuthorize("hasAnyRole('PROFESSOR', 'MANAGER', 'RESEARCHER')")
    public ResponseEntity<String> returnBook(@PathVariable int bookNum, HttpSession session) {

        bookService.returnBook(bookNum);
        return ResponseEntity.ok("책 반납이 완료되었습니다.");
    }

    @RequestMapping("/board")
    @Controller
    public static class BoardViewController {
        @GetMapping("/{board_num}/posts")
        public String getBoardPosts(){
            return "board";
        }

    }
}

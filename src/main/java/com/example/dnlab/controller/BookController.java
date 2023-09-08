package com.example.dnlab.controller;

import com.example.dnlab.domain.User;
import com.example.dnlab.dto.book.BookResDto;
import com.example.dnlab.dto.book.InsertBookReqDto;
import com.example.dnlab.domain.Book;
import com.example.dnlab.service.BookService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
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
@Slf4j
public class BookController {

    private final BookService bookService;

    //도서 추가
    @PostMapping("/insertBook")
    public ResponseEntity<BookResDto> insertBook(@RequestBody InsertBookReqDto req,HttpSession session){
        BookResDto res = bookService.insertBook(req);
        return ResponseEntity.ok(res);
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

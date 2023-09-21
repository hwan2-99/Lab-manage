package com.example.dnlab.controller;

import com.example.dnlab.domain.User;
import com.example.dnlab.domain.auth.PrincipalDetails;
import com.example.dnlab.dto.book.BookResDto;
import com.example.dnlab.dto.book.InsertBookReqDto;
import com.example.dnlab.domain.Book;
import com.example.dnlab.dto.rental.RentalResDto;
import com.example.dnlab.service.BookService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
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
    public ResponseEntity<BookResDto> insertBook(@AuthenticationPrincipal PrincipalDetails principalDetails, @RequestBody InsertBookReqDto req){
        BookResDto res = bookService.insertBook(req);
        return ResponseEntity.ok(res);
    }

    @GetMapping("/getAllBook")
    public List<Book>getAllBook(){
        return bookService.getAllBook();
    }

    @PutMapping("/borrow/{bookId}")
    public ResponseEntity<RentalResDto> borrowBook(@AuthenticationPrincipal PrincipalDetails principalDetails, @PathVariable int bookId) {
        int userId = principalDetails.getId();
        RentalResDto res = bookService.borrowBook(bookId,userId);

        return ResponseEntity.ok(res);
    }

    @PutMapping("/return/{bookId}")
    public ResponseEntity<RentalResDto> returnBook(@AuthenticationPrincipal PrincipalDetails principalDetails, @PathVariable int bookId) {
        int userId = principalDetails.getId();
        RentalResDto res = bookService.returnBook(bookId,userId);

        return ResponseEntity.ok(res);
    }

    @RequestMapping("/board")
    @Controller
    public static class BoardViewController {
        @GetMapping("/{board_id}/posts")
        public String getBoardPosts(){
            return "board";
        }

    }
}

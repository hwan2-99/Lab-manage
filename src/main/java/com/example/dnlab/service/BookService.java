package com.example.dnlab.service;

import com.example.dnlab.dto.book.BookResDto;
import com.example.dnlab.dto.book.InsertBookReqDto;
import com.example.dnlab.domain.Book;
import com.example.dnlab.domain.Rental;
import com.example.dnlab.repository.BookRepository;
import com.example.dnlab.repository.RentalRepository;
import com.example.dnlab.domain.User;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpSession;
import java.time.LocalDate;
import java.util.List;

@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class BookService {

    private final BookRepository bookRepository;
    private final RentalRepository rentalRepository;
    private final HttpSession session;
    LocalDate today = LocalDate.now();

    public BookResDto insertBook(InsertBookReqDto req) {
        Book book = Book.builder()
                .title(req.getTitle())
                .author(req.getAuthor())
                .borrowYN(false)
                .build();

        bookRepository.save(book);
        return BookResDto.builder()
                .title(book.getTitle())
                .author(book.getAuthor())
                .build();
    }

    public List<Book>getAllBook(){
        return bookRepository.findAll();
    }

    public void borrowBook(int bookNum){
        Book book = bookRepository.findById(bookNum);
        User user = (User)session.getAttribute("user");
        if(book.isBorrowYN()){
            throw new RuntimeException("이미 대여 된 도서입니다.");
        }
        log.info("대여자 pk: {}, 책 pk : {}",user.getId(), book.getId());
        Rental rental = Rental.builder()
                .user(user)
                .book(book)
                .rent_start_date(today)
                .build();

        rentalRepository.save(rental);
        bookRepository.updateBorrowY(book.getId());
    }

    public void returnBook(int bookNum){
        LocalDate rent_end_date = today;
        Book book = bookRepository.findById(bookNum);
        User user = (User)session.getAttribute("user");

        Rental rental = rentalRepository.findByUserIdAndBookId(user.getId(), book.getId());
        if(rental == null){
            throw new RuntimeException("대여하지 않은 도서입니다.");
        }
        rentalRepository.updateRentEndDate(rental.getId(), rent_end_date);
        bookRepository.updateBorrowN(book.getId());
    }
}



package com.example.dnlab.domain.book.service;

import com.example.dnlab.domain.book.dto.BookDto;
import com.example.dnlab.domain.book.entity.Book;
import com.example.dnlab.domain.book.entity.Rental;
import com.example.dnlab.domain.book.repository.BookRepository;
import com.example.dnlab.domain.book.repository.RentalMapper;
import com.example.dnlab.domain.user.entity.User;
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
    private final RentalMapper rentalMapper;
    private final HttpSession session;
    LocalDate today = LocalDate.now();

    public int insertBook(BookDto.insertBookReq req) {
        Book book = Book.builder()
                .title(req.getTitle())
                .author(req.getAuthor())
                .borrowYN(req.isBorrowYN())
                .build();

        bookRepository.save(book);
        return bookRepository.save(book).getNum();
    }

    public List<Book>getAllBook(){
        return bookRepository.getAllBooks();
    }

    public void borrowBook(int bookNum){
        Book book = bookRepository.getBookByNum(bookNum);
        User user = (User)session.getAttribute("user");
        if(book.isBorrowYN()){
            throw new RuntimeException("이미 대여 된 도서입니다.");
        }
        log.info("대여자 pk: {}, 책 pk : {}",user.getNum(), book.getNum());
        rentalMapper.insertRentInfo(new Rental(user.getNum(),book.getNum(),today));
        bookRepository.updateBorrowY(book.getNum());
    }

    public void returnBook(int bookNum){
        LocalDate rent_end_date = today;
        Book book = bookRepository.getBookByNum(bookNum);
        User user = (User)session.getAttribute("user");

        Rental rental = rentalMapper.selectRentalByBookNum(user.getNum(), book.getNum());
        if(rental == null){
            throw new RuntimeException("대여하지 않은 도서입니다.");
        }
        rentalMapper.updateRentInfo(rental.getNum(), rent_end_date);
        bookRepository.updateBorrowN(book.getNum());
    }
}



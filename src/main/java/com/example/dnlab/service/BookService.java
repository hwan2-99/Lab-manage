package com.example.dnlab.service;

import com.example.dnlab.dto.book.BookResDto;
import com.example.dnlab.dto.book.InsertBookReqDto;
import com.example.dnlab.domain.Book;
import com.example.dnlab.domain.Rental;
import com.example.dnlab.dto.rental.RentalResDto;
import com.example.dnlab.repository.BookRepository;
import com.example.dnlab.repository.RentalRepository;
import com.example.dnlab.domain.User;
import com.example.dnlab.repository.UserRepository;
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
    private final UserRepository userRepository;
    private final RentalRepository rentalRepository;
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

    public RentalResDto borrowBook(int bookId, int userId){
        Book book = bookRepository.findById(bookId);
        User user = userRepository.findById(userId);

        if(book.isBorrowYN()){
            throw new RuntimeException("이미 대여 된 도서입니다.");
        }

        log.info("대여자 pk: {}, 책 pk : {}",userId, book.getId());
        Rental rental = Rental.builder()
                .user(user)
                .book(book)
                .rent_start_date(today)
                .build();

        rentalRepository.save(rental);
        bookRepository.updateBorrowY(book.getId());

        return RentalResDto.builder()
                .rentalId(rental.getId())
                .build();
    }

    public RentalResDto returnBook(int bookId, int userId){
        LocalDate rent_end_date = today;
        Book book = bookRepository.findById(bookId);

        Rental rental = rentalRepository.findByUserIdAndBookId(userId, book.getId());
        if(rental == null){
            throw new RuntimeException("대여하지 않은 도서입니다.");
        }
        rentalRepository.updateRentEndDate(rental.getId(), rent_end_date);
        bookRepository.updateBorrowN(book.getId());

        return RentalResDto.builder()
                .rentalId(rental.getId())
                .build();
    }
}



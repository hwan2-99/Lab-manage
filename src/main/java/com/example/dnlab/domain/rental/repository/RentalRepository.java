package com.example.dnlab.domain.rental.repository;

import com.example.dnlab.domain.rental.entity.Rental;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;

public interface RentalRepository extends JpaRepository<Rental, Integer> {
    Rental findByUserNumAndBookNum(int userNum, int bookNum);

    @Modifying
    @Query("UPDATE Rental r SET r.rent_end_date = :rentEndDate WHERE r.num = :num")
    void updateRentEndDate(int num, LocalDate rentEndDate);
}

package com.example.dnlab.repository;

import com.example.dnlab.domain.Rental;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDate;

public interface RentalRepository extends JpaRepository<Rental, Integer> {
    Rental findByUserIdAndBookId(int userId, int booId);

    @Modifying
    @Query("UPDATE Rental r SET r.rent_end_date = :rentEndDate WHERE r.id = :id")
    void updateRentEndDate(int id, LocalDate rentEndDate);
}

package com.example.dnlab.domain.using.repository;

import com.example.dnlab.domain.using.entity.Using;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;

public interface UsingRepository extends JpaRepository<Using, Integer> {
    Using findByUserNumAndEquipmentNum(int userNum, int equipmentNum);

}

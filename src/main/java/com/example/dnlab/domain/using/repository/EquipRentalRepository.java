package com.example.dnlab.domain.using.repository;

import com.example.dnlab.domain.using.entity.EquipRental;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EquipRentalRepository extends JpaRepository<EquipRental, Integer> {
    EquipRental findByUserNumAndEquipmentNum(int userNum, int equipmentNum);

}

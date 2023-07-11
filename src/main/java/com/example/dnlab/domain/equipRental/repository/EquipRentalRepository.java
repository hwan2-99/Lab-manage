package com.example.dnlab.domain.equipRental.repository;

import com.example.dnlab.domain.equipRental.entity.EquipRental;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EquipRentalRepository extends JpaRepository<EquipRental, Integer> {
    EquipRental findByUserNumAndEquipmentNum(int userNum, int equipmentNum);

}

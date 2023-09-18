package com.example.dnlab.repository;

import com.example.dnlab.domain.EquipRental;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EquipRentalRepository extends JpaRepository<EquipRental, Integer> {
    EquipRental findByUserIdAndEquipmentId(int userId, int equipmentId);

}

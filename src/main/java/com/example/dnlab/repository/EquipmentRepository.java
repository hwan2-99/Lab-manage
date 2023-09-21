package com.example.dnlab.repository;

import com.example.dnlab.domain.Equipment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.awt.print.Pageable;
import java.util.List;

public interface EquipmentRepository extends JpaRepository<Equipment, Integer> {
    // 대여중인 장비 상태를 표시하기 위한 메소드
    @Modifying
    @Query("UPDATE Equipment e SET e.usingYN = true WHERE e.id = :id")
    void updateUsingY(int id);

    // 대여종료한 장비 상태를 나누기 위한 메소드
    @Modifying
    @Query("UPDATE Equipment e SET e.usingYN = false WHERE e.id = :id")
    void updateUsingN(int id);

    Equipment findById(int equipmentId);

    List<Equipment> findAllDesc(Pageable pageable);

}

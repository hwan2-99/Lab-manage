package com.example.dnlab.domain.equipment.repository;

import com.example.dnlab.domain.equipment.entity.Equipment;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface EquipmentRepository extends JpaRepository<Equipment, Integer> {
    // 대여중인 장비 상태를 표시하기 위한 메소드
    @Modifying
    @Query("UPDATE Equipment e SET e.usingYN = true WHERE e.num = :num")
    void updateUsingY(int num);

    // 대여종료한 장비 상태를 나누기 위한 메소드
    @Modifying
    @Query("UPDATE Equipment e SET e.usingYN = false WHERE e.num = :num")
    void updateUsingN(int num);

    Equipment getEquipmentByNum(int equipmentNum);

}

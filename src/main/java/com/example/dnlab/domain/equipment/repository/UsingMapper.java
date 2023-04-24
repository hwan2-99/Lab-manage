package com.example.dnlab.domain.equipment.repository;

import com.example.dnlab.domain.book.entity.Rental;
import com.example.dnlab.domain.equipment.entity.Using;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;

@Mapper
@Repository
public interface UsingMapper {
    void insertUseInfo(Using using);
    Using selectUsingByEquipmentNum(int user_num,int equipment_num);

    void updateUsingInfo(int num, LocalDate rent_end_date);
}

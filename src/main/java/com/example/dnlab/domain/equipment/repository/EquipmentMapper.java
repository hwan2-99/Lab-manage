package com.example.dnlab.domain.equipment.repository;

import com.example.dnlab.domain.book.entity.Book;
import com.example.dnlab.domain.equipment.entity.Equipment;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface EquipmentMapper {
    void createEquipment(Equipment equipment);

}

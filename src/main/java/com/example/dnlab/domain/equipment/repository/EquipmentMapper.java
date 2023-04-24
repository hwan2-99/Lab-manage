package com.example.dnlab.domain.equipment.repository;

import com.example.dnlab.domain.book.entity.Book;
import com.example.dnlab.domain.equipment.entity.Equipment;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface EquipmentMapper {
    void createEquipment(Equipment equipment); // 장비 추가
    List<Equipment> getAllEquipment();
    void updateUsingY(int num); // 대여중인 장비상태를 표시하기 위한 매퍼
    void updateUsingN(int num); // 대여종료한 장비상태를 나누기 위한 매퍼
    Equipment getEquipmentByNum(int EquipmentNum); // 장비 pk로 장비 정보 가져오는 매퍼

}

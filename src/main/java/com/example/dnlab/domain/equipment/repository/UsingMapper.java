package com.example.dnlab.domain.equipment.repository;

import com.example.dnlab.domain.book.entity.Rental;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface UsingMapper {
    void insertUseInfo(Rental rental);
}

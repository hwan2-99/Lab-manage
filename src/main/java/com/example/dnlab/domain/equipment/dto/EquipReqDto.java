package com.example.dnlab.domain.equipment.dto;

import lombok.Getter;

import java.util.Date;
@Getter
public class EquipReqDto {
    private String name;
    private Date purchase_date;
    private int price;
}

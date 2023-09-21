package com.example.dnlab.dto.equipment;

import com.example.dnlab.domain.Equipment;
import lombok.Getter;

import java.util.Date;

@Getter
public class EquipmentListResponseDto {
    private int id;
    private String name;
    private Date purchaseDate;
    private int price;
    private boolean usingYN;

    public EquipmentListResponseDto(Equipment entity){
        this.id = entity.getId();
        this.name = entity.getName();
        this.price = entity.getPrice();
        this.purchaseDate = entity.getPurchaseDate();
        this.usingYN = entity.isUsingYN();
    }
}

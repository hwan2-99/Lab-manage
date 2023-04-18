package com.example.dnlab.domain.equipment.dto;

import lombok.*;

import java.util.Date;

public class EquipmentDto {

    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    public static class createEquipmentReq{
        private String name;
        private Date purchase_date;
        private int price;
    }
}

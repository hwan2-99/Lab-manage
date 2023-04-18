package com.example.dnlab.domain.equipment.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Equipment {
    private int num;
    private String name;
    private Date purchase_date;
    private int price;
    private boolean usingYN;

    public Equipment(String name, Date purchase_date, int price) {
        this.name = name;
        this.purchase_date = purchase_date;
        this.price = price;
    }
}

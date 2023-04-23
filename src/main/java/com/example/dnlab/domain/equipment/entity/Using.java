package com.example.dnlab.domain.equipment.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Using {
    private int num;
    private int user_num;
    private int equipment_num;
    private LocalDate using_start_date;
    private LocalDate using_end_date;
}

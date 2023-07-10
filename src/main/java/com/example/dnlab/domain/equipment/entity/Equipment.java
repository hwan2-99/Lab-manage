package com.example.dnlab.domain.equipment.entity;

import com.example.dnlab.domain.using.entity.Using;
import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Builder
public class Equipment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "num")
    private int num;
    @Column
    private String name;
    @Column
    private Date purchaseDate;
    @Column
    private int price;
    @Column
    private boolean usingYN;

    @OneToMany(mappedBy = "equipment")
    private List<Using> usings = new ArrayList<>();

}

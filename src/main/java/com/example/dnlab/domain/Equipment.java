package com.example.dnlab.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
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
    @JsonIgnore
    private List<EquipRental> equipRental = new ArrayList<>();

}

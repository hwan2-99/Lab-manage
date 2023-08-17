package com.example.dnlab.domain;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Builder
public class EquipRental {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "num")
    private int num;

    @Column(name = "using_start_date")
    private LocalDate usingStartDate;

    @Column(name = "using_end_date")
    private LocalDate usingEndDate;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "equipment_num")
    private Equipment equipment;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_num")
    private User user;
}

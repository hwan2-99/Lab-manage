package com.example.dnlab.domain.using.entity;

import com.example.dnlab.domain.equipment.entity.Equipment;
import com.example.dnlab.domain.user.entity.User;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Builder
public class Using {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "num")
    private int num;
    @Column
    private LocalDate using_start_date;
    @Column
    private LocalDate using_end_date;

    @ManyToOne
    @JoinColumn(name = "equipment_num")
    private Equipment equipment;

    @ManyToOne
    @JoinColumn(name = "user_num")
    private User user;
}

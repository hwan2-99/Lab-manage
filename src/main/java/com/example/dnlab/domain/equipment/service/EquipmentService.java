package com.example.dnlab.domain.equipment.service;

import com.example.dnlab.domain.equipment.dto.EquipmentDto;
import com.example.dnlab.domain.equipment.entity.Equipment;
import com.example.dnlab.domain.using.entity.Using;
import com.example.dnlab.domain.equipment.repository.EquipmentRepository;
import com.example.dnlab.domain.using.repository.UsingRepository;
import com.example.dnlab.domain.user.entity.User;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpSession;
import java.time.LocalDate;

@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class EquipmentService {

    private final HttpSession session;
    private final EquipmentRepository equipmentRepository;
    private final UsingRepository usingRepository;
    LocalDate today = LocalDate.now();

    // 장비추가
    public void createEquipment(EquipmentDto.createEquipmentReq req){
        log.info("이름 : {}, 장비 가격: {}, 구매일 : {} ",req.getName(), req.getPrice(), req.getPurchase_date());
        Equipment equipment = Equipment.builder()
                .name(req.getName())
                .purchaseDate(req.getPurchase_date())
                .price(req.getPrice())
                .build();

        equipmentRepository.save(equipment);
    }

    // 장비 대여 서비스
    public void borrowEquipment(int equipmentNum) {
        Equipment equipment = equipmentRepository.getEquipmentByNum(equipmentNum);
        User user = (User) session.getAttribute("user");
        if (equipment.isUsingYN()) {
            throw new RuntimeException("이미 대여 된 장비입니다.");
        }
        log.info("대여자 pk: {}, 책 pk : {}", user.getNum(), equipment.getNum());

        Using using = Using.builder()
                .user(user)
                .equipment(equipment)
                .using_start_date(today)
                .build();

        usingRepository.save(using);
        equipmentRepository.updateUsingY(equipment.getNum());
    }

    // 장비 반납 서비스
    public void returnEquipment(int equipmentNum) {
        LocalDate rentEndDate = today;
        Equipment equipment = equipmentRepository.getEquipmentByNum(equipmentNum);
        User user = (User) session.getAttribute("user");

        Using using = usingRepository.findByUserNumAndEquipmentNum(user.getNum(), equipment.getNum());
        if (using == null) {
            throw new RuntimeException("대여하지 않은 장비입니다.");
        }

        Using updatedUsing = Using.builder()
                .num(using.getNum())
                .user(user)
                .equipment(equipment)
                .using_start_date(using.getUsing_start_date())
                .using_end_date(rentEndDate)
                .build();

        usingRepository.save(updatedUsing);
        equipmentRepository.updateUsingN(equipment.getNum());
    }

}

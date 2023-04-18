package com.example.dnlab.domain.equipment.service;

import com.example.dnlab.domain.equipment.dto.EquipmentDto;
import com.example.dnlab.domain.equipment.entity.Equipment;
import com.example.dnlab.domain.equipment.repository.EquipmentMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class EquipmentService {

    private final EquipmentMapper equipmentMapper;

    public void createEquipment(EquipmentDto.createEquipmentReq req){
        log.info("이름 : {}, 장비 가격: {}, 구매일 : {} ",req.getName(), req.getPrice(), req.getPurchase_date());
        equipmentMapper.createEquipment(new Equipment(req.getName(),req.getPurchase_date(),req.getPrice()));
    }
}

package com.example.dnlab.domain.equipment.service;

import com.example.dnlab.domain.book.entity.Book;
import com.example.dnlab.domain.book.entity.Rental;
import com.example.dnlab.domain.equipment.dto.EquipmentDto;
import com.example.dnlab.domain.equipment.entity.Equipment;
import com.example.dnlab.domain.equipment.entity.Using;
import com.example.dnlab.domain.equipment.repository.EquipmentMapper;
import com.example.dnlab.domain.equipment.repository.UsingMapper;
import com.example.dnlab.domain.user.entity.User;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpSession;
import javax.websocket.Session;
import java.time.LocalDate;

@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class EquipmentService {

    private final HttpSession session;
    private final EquipmentMapper equipmentMapper;
    private final UsingMapper usingMapper;
    LocalDate today = LocalDate.now();

    // 장비추가
    public void createEquipment(EquipmentDto.createEquipmentReq req){
        log.info("이름 : {}, 장비 가격: {}, 구매일 : {} ",req.getName(), req.getPrice(), req.getPurchase_date());
        equipmentMapper.createEquipment(new Equipment(req.getName(),req.getPurchase_date(),req.getPrice()));
    }

    // 장비 대여 서비스
    public void borrowEquipment(int equipmentNum){
        Equipment equipment = equipmentMapper.getEquipmentByNum(equipmentNum);
        User user = (User)session.getAttribute("user");
        if(equipment.isUsingYN()){
            throw new RuntimeException("이미 대여 된 장비입니다.");
        }
        log.info("대여자 pk: {}, 책 pk : {}",user.getNum(), equipment.getNum());
        usingMapper.insertUseInfo(new Using(user.getNum(),equipment.getNum(),today));
        equipmentMapper.updateUsingY(equipment.getNum());
    }

    // 장비 반납 서비스
    public void returnEquipment(int equipmentNum){
        LocalDate rent_end_date = today;
        Equipment equipment = equipmentMapper.getEquipmentByNum(equipmentNum);
        User user = (User)session.getAttribute("user");

        Using using = usingMapper.selectUsingByEquipmentNum(user.getNum(), equipment.getNum());
        if(using == null){
            throw new RuntimeException("대여하지 않은 장비입니다.");
        }
        usingMapper.updateUsingInfo(using.getNum(), rent_end_date);
        equipmentMapper.updateUsingN(equipment.getNum());
    }
}

package com.example.dnlab.service;

import com.example.dnlab.dto.equipment.EquipmentListResponseDto;
import com.example.dnlab.dto.equipment.EquipmentReqDto;

import com.example.dnlab.domain.Equipment;
import com.example.dnlab.dto.equipment.EquipmentResDto;
import com.example.dnlab.dto.equipmentRental.EquipmentRentalResDto;
import com.example.dnlab.repository.EquipmentRepository;
import com.example.dnlab.domain.EquipRental;
import com.example.dnlab.domain.User;
import com.example.dnlab.repository.EquipRentalRepository;
import com.example.dnlab.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpSession;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class EquipmentService {

    private final EquipmentRepository equipmentRepository;
    private final EquipRentalRepository equipRentalRepository;
    private final UserRepository userRepository;
    LocalDate today = LocalDate.now();

    // 장비추가
    public EquipmentResDto createEquipment(EquipmentReqDto req){
        log.info("이름 : {}, 장비 가격: {}, 구매일 : {} ",req.getName(), req.getPrice(), req.getPurchase_date());
        Equipment equipment = Equipment.builder()
                .name(req.getName())
                .purchaseDate(req.getPurchase_date())
                .price(req.getPrice())
                .build();

        equipmentRepository.save(equipment);
        return EquipmentResDto.builder()
                .name(equipment.getName())
                .build();
    }

    // 장비 대여 서비스
    public EquipmentRentalResDto borrowEquipment(int equipmentId, int userId) {
        Equipment equipment = equipmentRepository.findById(equipmentId);
        User user = userRepository.findById(userId);
        if (equipment.isUsingYN()) {
            throw new RuntimeException("이미 대여 된 장비입니다.");
        }
        log.info("대여자 pk: {}, 책 pk : {}", user.getId(), equipment.getId());

        EquipRental equipRental = EquipRental.builder()
                .user(user)
                .equipment(equipment)
                .usingStartDate(today)
                .build();

        equipRentalRepository.save(equipRental);
        equipmentRepository.updateUsingY(equipment.getId());

        return EquipmentRentalResDto.builder()
                .rentalId(equipRental.getId())
                .build();
    }

    // 장비 반납 서비스
    public EquipmentRentalResDto returnEquipment(int equipmentId, int userId) {
        LocalDate rentEndDate = today;
        Equipment equipment = equipmentRepository.findById(equipmentId);
        User user = userRepository.findById(userId);

        EquipRental equipRental = equipRentalRepository.findByUserIdAndEquipmentId(user.getId(), equipment.getId());
        if (equipRental == null) {
            throw new RuntimeException("대여하지 않은 장비입니다.");
        }

        EquipRental updatedUsing = equipRental.builder()
                .id(equipRental.getId())
                .user(user)
                .equipment(equipment)
                .usingStartDate(equipRental.getUsingStartDate())
                .usingEndDate(rentEndDate)
                .build();

        equipRentalRepository.save(updatedUsing);
        equipmentRepository.updateUsingN(equipment.getId());

        return EquipmentRentalResDto.builder()
                .rentalId(equipRental.getId())
                .build();
    }

    public List<EquipmentListResponseDto> getAllDesc(Pageable pageable){
            List<Equipment> equipmentList = equipmentRepository.findAllByOrderByPurchaseDateDesc(pageable);
            return equipmentList.stream()
                    .map(EquipmentListResponseDto::new)
                    .collect(Collectors.toList());
        }

}

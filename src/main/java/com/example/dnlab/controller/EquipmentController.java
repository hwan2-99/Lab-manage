package com.example.dnlab.controller;

import com.example.dnlab.domain.auth.PrincipalDetails;
import com.example.dnlab.dto.equipment.EquipmentListResponseDto;
import com.example.dnlab.dto.equipment.EquipmentReqDto;

import com.example.dnlab.dto.equipment.EquipmentResDto;
import com.example.dnlab.dto.equipmentRental.EquipmentRentalResDto;
import com.example.dnlab.dto.rental.RentalResDto;
import com.example.dnlab.service.EquipmentService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/equipment")
@RequiredArgsConstructor
@Slf4j
public class EquipmentController {
    private final EquipmentService equipmentService;

    @PostMapping("/createEquipment")
    public ResponseEntity<EquipmentResDto> createTodo(@RequestBody EquipmentReqDto req){
        EquipmentResDto res = equipmentService.createEquipment(req);
        return ResponseEntity.ok(res);
    }

    @PutMapping("/borrow/{equipmentId}")
    public ResponseEntity<EquipmentRentalResDto> borrowEquipment(@AuthenticationPrincipal PrincipalDetails principalDetails, @PathVariable int equipmentId) {
        int userId = principalDetails.getId();
        EquipmentRentalResDto res = equipmentService.borrowEquipment(equipmentId,userId);

        return ResponseEntity.ok(res);
    }

    @PutMapping("/return/{equipmentId}")
    public ResponseEntity<EquipmentRentalResDto> returnEquipment(@AuthenticationPrincipal PrincipalDetails principalDetails, @PathVariable int equipmentId) {
        int userId = principalDetails.getId();
        EquipmentRentalResDto res = equipmentService.returnEquipment(equipmentId,userId);

        return ResponseEntity.ok(res);
    }

    @GetMapping("/AllEquipment")
    public ResponseEntity<List<EquipmentListResponseDto>> getAllDesc() {
        Pageable pageable = PageRequest.of(0, 10);

        return ResponseEntity.ok(equipmentService.getAllDesc(pageable));
    }
}

package com.example.dnlab.domain.equipment.controller;

import com.example.dnlab.domain.equipment.dto.EquipReqDto;

import com.example.dnlab.domain.equipment.service.EquipmentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/equipment")
@RequiredArgsConstructor
public class EquipmentController {
    private final EquipmentService service;

    @PostMapping("/createEquipment")
    @PreAuthorize("hasAnyRole('PROFESSOR', 'MANAGER')")
    public ResponseEntity<Void> createTodo(@RequestBody EquipReqDto req){
        service.createEquipment(req);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }
}

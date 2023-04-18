package com.example.dnlab.domain.equipment.controller;

import com.example.dnlab.domain.equipment.dto.EquipmentDto;
import com.example.dnlab.domain.equipment.repository.EquipmentMapper;
import com.example.dnlab.domain.equipment.service.EquipmentService;
import com.example.dnlab.domain.toDo.dto.ToDoDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<Void> createTodo(@RequestBody EquipmentDto.createEquipmentReq req){
        service.createEquipment(req);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }
}

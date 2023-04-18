package com.example.dnlab.domain.equipment.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/equipment")
@Controller
public class EquipmentViewController {
    @GetMapping("/createEquipment")
    public String createEquipment(){
        return "createEquipment";
    }
}

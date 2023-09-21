package com.example.dnlab.dto.equipment;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class EquipmentResDto {
    private String name;
    private boolean usingYN;
}

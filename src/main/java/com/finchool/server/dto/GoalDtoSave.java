package com.finchool.server.dto;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class GoalDtoSave {
    private String name;
    private BigDecimal targetAmount;
    private BigDecimal currentAmount;
    private String photoUrl;
    private int androidId;
}

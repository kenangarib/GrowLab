package com.growlabtask1.growlabtask1.dto;

import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
public class ResponseDto {

    private Long id;
    private String name;
    private BigDecimal price;
    private LocalDate createdDate;
    private LocalDate updatedDate;
}

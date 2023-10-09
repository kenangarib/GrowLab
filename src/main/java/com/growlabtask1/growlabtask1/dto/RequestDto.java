package com.growlabtask1.growlabtask1.dto;

import jakarta.validation.constraints.*;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class RequestDto {

    @NotBlank(message = "name must not be empty")
    @Size(min = 2, max = 25, message = "name must be between 5 and 25")
    private String name;

    @NotNull
    @Min(value = 0, message = "0 is not valid")
    @PositiveOrZero(message = "value must not be minus")
    private BigDecimal price;
}

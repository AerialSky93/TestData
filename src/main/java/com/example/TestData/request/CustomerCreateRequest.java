package com.example.TestData.request;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
public class CustomerCreateRequest {

    @NotBlank
    private String firstName;

    private int employeeId;

    @Min(1)
    @Max(1000)
    private BigDecimal feeAmount;
    private LocalDateTime enrollmentDate;
    private boolean activeFlag;
}

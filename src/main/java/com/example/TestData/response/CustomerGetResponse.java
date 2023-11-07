package com.example.TestData.response;

import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
public class CustomerGetResponse {
    private String firstName;
    private int employeeId;
    private BigDecimal feeAmount;
    private boolean activeFlag;
    private LocalDateTime enrollmentDate;
}

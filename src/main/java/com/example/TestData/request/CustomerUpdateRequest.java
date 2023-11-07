package com.example.TestData.request;

import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
public class CustomerUpdateRequest {
    private int customerId;
    private String firstName;
    private int employeeId;
    private BigDecimal feeAmount;
    private LocalDateTime enrollmentDate;
    private boolean activeFlag;
}

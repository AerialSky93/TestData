package com.example.TestData.response;

import lombok.Data;

import java.math.BigDecimal;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.OffsetDateTime;

@Data
public class CustomerGetResponse {
    private int customerId;
    private String firstName;
    private int employeeId;
    private BigDecimal feeAmount;
    private boolean activeFlag;
    private OffsetDateTime enrollmentDate;
}

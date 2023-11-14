package com.example.TestData.request;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class JobBidCreateRequest {

    @NotNull
    private BigDecimal bidAmount;
    @NotNull
    private int jobPostingId;

}

package com.example.TestData.request;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

import java.math.BigDecimal;
import java.time.OffsetDateTime;

@Data
public class JobPostingCreateRequest {

    @NotBlank
    private String jobDescription;

    @NotBlank
    private String jobRequirements;

    @NotBlank
    private String posterLastName;

    @NotBlank
    private String contactPhone;

}

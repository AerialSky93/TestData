package com.example.TestData.response;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class JobPostingRecentItem {
    private int jobPostingId;
    private String jobDescription;
    private String jobRequirements;
    private String posterLastName;
    private String contactPhone;
}

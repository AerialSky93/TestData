package com.example.TestData.response;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class JobPostingRecentGetResponse {
    List<JobPostingRecentItem> jobPostingRecentResponse = new ArrayList<>();
}

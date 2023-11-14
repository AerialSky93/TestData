package com.example.TestData.service;

import com.example.TestData.request.JobPostingCreateRequest;
import com.example.TestData.response.CustomerGetResponse;
import com.example.TestData.response.JobPostingCreateResponse;

public interface JobPostingService {
    JobPostingCreateResponse createJobPosting(JobPostingCreateRequest jobPostingCreateRequest);

}

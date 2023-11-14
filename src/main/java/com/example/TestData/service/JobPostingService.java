package com.example.TestData.service;

import com.example.TestData.request.JobPostingCreateRequest;
import com.example.TestData.response.CustomerGetResponse;
import com.example.TestData.response.JobPostingCreateResponse;
import com.example.TestData.response.JobPostingRecentGetResponse;

public interface JobPostingService {
    JobPostingCreateResponse createJobPosting(JobPostingCreateRequest jobPostingCreateRequest);
    JobPostingRecentGetResponse getMostRecentJobPostings() ;

}

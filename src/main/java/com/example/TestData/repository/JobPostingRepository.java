package com.example.TestData.repository;

import com.example.TestData.request.CustomerCreateRequest;
import com.example.TestData.request.CustomerUpdateRequest;
import com.example.TestData.request.JobPostingCreateRequest;
import com.example.TestData.response.CustomerGetResponse;
import com.example.TestData.response.JobPostingCreateResponse;
import com.example.TestData.response.JobPostingRecentGetResponse;

import java.util.List;

public interface JobPostingRepository {
    JobPostingCreateResponse createJobPosting(JobPostingCreateRequest jobPostingCreateRequest);
    JobPostingRecentGetResponse getMostRecentJobPostings();
}

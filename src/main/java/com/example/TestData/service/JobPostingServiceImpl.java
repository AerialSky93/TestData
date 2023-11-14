package com.example.TestData.service;

import com.example.TestData.repository.CustomerRepository;
import com.example.TestData.repository.JobPostingRepository;
import com.example.TestData.request.JobPostingCreateRequest;
import com.example.TestData.response.CustomerGetResponse;
import com.example.TestData.response.JobPostingCreateResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

@Service
public class JobPostingServiceImpl implements JobPostingService {
    JobPostingRepository jobPostingRepository;
    @Autowired
    public JobPostingServiceImpl(JobPostingRepository jobPostingRepository) {
        this.jobPostingRepository = jobPostingRepository;

    }

    public JobPostingCreateResponse createJobPosting(JobPostingCreateRequest jobPostingCreateRequest) {
        return jobPostingRepository.createJobPosting(jobPostingCreateRequest);
    }
}


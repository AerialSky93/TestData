package com.example.TestData.service;

import com.example.TestData.repository.JobBidRepository;
import com.example.TestData.repository.JobPostingRepository;
import com.example.TestData.request.JobBidCreateRequest;
import com.example.TestData.request.JobPostingCreateRequest;
import com.example.TestData.response.JobBidCreateResponse;
import com.example.TestData.response.JobPostingCreateResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class JobBidServiceImpl implements JobBidService {
    JobBidRepository jobBidRepository;
    @Autowired
    public JobBidServiceImpl(JobBidRepository jobBidRepository) {
        this.jobBidRepository = jobBidRepository;

    }

    public JobBidCreateResponse createJobBid(JobBidCreateRequest jobBidCreateRequest) {
        return jobBidRepository.createJobBid(jobBidCreateRequest);
    }
}

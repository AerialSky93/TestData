package com.example.TestData.service;

import com.example.TestData.request.JobBidCreateRequest;
import com.example.TestData.request.JobPostingCreateRequest;
import com.example.TestData.response.JobBidCreateResponse;
import com.example.TestData.response.JobPostingCreateResponse;

public interface JobBidService {
    JobBidCreateResponse createJobBid(JobBidCreateRequest jobBidCreateRequest);

}

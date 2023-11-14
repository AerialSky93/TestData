package com.example.TestData.repository;


import com.example.TestData.request.JobBidCreateRequest;
import com.example.TestData.response.JobBidCreateResponse;

public interface JobBidRepository {
    JobBidCreateResponse createJobBid(JobBidCreateRequest jobBidCreateRequest);

}

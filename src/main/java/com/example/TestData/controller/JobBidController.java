package com.example.TestData.controller;

import com.example.TestData.event.CustomerPublisher;
import com.example.TestData.repository.CustomerRepository;
import com.example.TestData.request.CustomerCreateRequest;
import com.example.TestData.request.CustomerUpdateRequest;
import com.example.TestData.request.JobBidCreateRequest;
import com.example.TestData.request.JobPostingCreateRequest;
import com.example.TestData.response.CustomerGetResponse;
import com.example.TestData.response.JobBidCreateResponse;
import com.example.TestData.response.JobPostingCreateResponse;
import com.example.TestData.service.CustomerService;
import com.example.TestData.service.JobBidService;
import com.example.TestData.service.JobPostingService;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@RestController
@RequestMapping("/api")
@CrossOrigin
public class JobBidController {

    private JobBidService jobBidService;

    @Autowired
    public JobBidController(JobBidService jobBidService) {
        this.jobBidService = jobBidService;
    }


    @PostMapping("/jobbid")
    public JobBidCreateResponse createJobBid(@Valid @RequestBody JobBidCreateRequest jobBidCreateRequest) {
        JobBidCreateResponse jobBidCreateResponse  = jobBidService.createJobBid(jobBidCreateRequest);
        return jobBidCreateResponse;
    }


    //public CustomerGetResponse getCustomerById(HttpServletResponse response, @PathVariable("id") int id) {
}
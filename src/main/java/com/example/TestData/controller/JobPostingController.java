package com.example.TestData.controller;

import com.example.TestData.event.CustomerPublisher;
import com.example.TestData.repository.CustomerRepository;
import com.example.TestData.request.CustomerCreateRequest;
import com.example.TestData.request.CustomerUpdateRequest;
import com.example.TestData.request.JobPostingCreateRequest;
import com.example.TestData.response.CustomerGetResponse;
import com.example.TestData.response.JobPostingCreateResponse;
import com.example.TestData.response.JobPostingRecentGetResponse;
import com.example.TestData.service.CustomerService;
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
public class JobPostingController {

    private JobPostingService jobPostingService;

    @Autowired
    public JobPostingController(JobPostingService jobPostingService) {
        this.jobPostingService = jobPostingService;
    }


    @PostMapping("/jobposting")
    public JobPostingCreateResponse createJobPost(@Valid @RequestBody JobPostingCreateRequest jobPostingCreateRequest) {
        JobPostingCreateResponse jobPostingCreateResponse  = jobPostingService.createJobPosting(jobPostingCreateRequest);
        return jobPostingCreateResponse;
    }

    @GetMapping("/jobposting/get10MostRecent")
    public JobPostingRecentGetResponse getMostRecentJobPostings() {
        JobPostingRecentGetResponse jobPostingCreateResponse  = jobPostingService.getMostRecentJobPostings();
        return jobPostingCreateResponse;
    }



    //public CustomerGetResponse getCustomerById(HttpServletResponse response, @PathVariable("id") int id) {
}
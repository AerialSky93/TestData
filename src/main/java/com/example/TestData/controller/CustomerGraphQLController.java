package com.example.TestData.controller;

import com.example.TestData.event.CustomerPublisher;
import com.example.TestData.repository.CustomerRepository;
import com.example.TestData.response.CustomerGetResponse;
import com.example.TestData.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.web.bind.annotation.*;
import org.springframework.stereotype.Controller;

@Controller
@CrossOrigin
public class CustomerGraphQLController {

    private CustomerService customerService;
    private CustomerRepository customerRepository;
    private CustomerPublisher customerPublisher;

    @Autowired
    public CustomerGraphQLController(CustomerService customerService, CustomerRepository customerRepository, CustomerPublisher customerPublisher) {
        this.customerRepository = customerRepository;
        this.customerPublisher = customerPublisher;
        this.customerService = customerService;
    }

    @QueryMapping
    public CustomerGetResponse getCustomerByIdGraphQL(@Argument int customerId) {
        CustomerGetResponse customerGetResponse = customerRepository.findByCustomerId(customerId);
        return customerGetResponse;
    }
}
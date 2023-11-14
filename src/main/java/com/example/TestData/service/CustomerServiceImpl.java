package com.example.TestData.service;

import com.example.TestData.repository.CustomerRepository;
import com.example.TestData.response.CustomerGetResponse;
import com.example.TestData.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;

import org.springframework.stereotype.Repository;


@Repository
public class CustomerServiceImpl implements CustomerService {


    CustomerRepository customerRepository;
    @Autowired
    public CustomerServiceImpl(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;

    }

    @Cacheable("customers")
    public CustomerGetResponse findByCustomerIdSvc(int customerId) {
        return customerRepository.findByCustomerId(customerId);
    }
}


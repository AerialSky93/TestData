package com.example.TestData.controller;


import com.example.TestData.repository.CustomerRepository;
import com.example.TestData.request.CustomerCreateRequest;
import com.example.TestData.request.CustomerUpdateRequest;
import com.example.TestData.response.CustomerGetResponse;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@RestController
@RequestMapping("/api")
public class CustomerController {

    private CustomerRepository customerRepository;

    @Autowired
    public CustomerController(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    @GetMapping("/customer/{id}")
    public CustomerGetResponse getCustomerById(HttpServletResponse response, @PathVariable("id") int id) {
        CustomerGetResponse customerGetResponse = customerRepository.findByCustomerId(id);

        if (customerGetResponse == null) {
            response.setStatus(HttpStatus.NO_CONTENT.value());
        }
        return customerGetResponse;
    }

    @PostMapping("/customer")
    public int createCustomer(HttpServletResponse response, @Valid @RequestBody CustomerCreateRequest customerCreateRequest) {
        int newId = -1;
        try {
            newId = customerRepository.createCustomerId(customerCreateRequest);
        } catch (Exception e) {
            response.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
        }
        return newId;
    }

    @PostMapping("/customerRepeat")
    public void createCustomer2(HttpServletResponse response, @Valid @RequestBody CustomerCreateRequest customerCreateRequest) {
        ExecutorService executor = Executors.newFixedThreadPool(10);

        Runnable runnableTask = () -> {
            createCustomer(response, customerCreateRequest);
        };

        for (int i = 0; i < 10; i++) {
            executor.execute(runnableTask);
        }

        executor.shutdown();
    }

    @PutMapping("/customer")
    public int updateCustomer(HttpServletResponse response, @RequestBody CustomerUpdateRequest customerUpdateRequest) {
        int newId = -1;
        try {
            newId = customerRepository.updateCustomer(customerUpdateRequest);
        } catch (Exception e) {
            response.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
        }
        return newId;
    }
}
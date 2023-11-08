package com.example.TestData.controller;

import com.example.TestData.repository.CustomerRepository;
import com.example.TestData.request.CustomerCreateRequest;
import com.example.TestData.request.CustomerUpdateRequest;
import com.example.TestData.response.CustomerGetResponse;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
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

    @GetMapping("/customer/{customerId}")
    public CustomerGetResponse getCustomerById(@PathVariable("customerId") int customerId) {
        CustomerGetResponse customerGetResponse = customerRepository.findByCustomerId(customerId);
        return customerGetResponse;
    }

    @GetMapping("/customer")
    public List<CustomerGetResponse> getAllCustomer() {
        List<CustomerGetResponse> customerGetResponseList = customerRepository.findAll();
        return customerGetResponseList;
    }

    @PostMapping("/customer")
    public int createCustomer(@Valid @RequestBody CustomerCreateRequest customerCreateRequest) {
        int newId  = customerRepository.createCustomerId(customerCreateRequest);
        return newId;
    }

    @PutMapping("/customer")
    public int updateCustomer(HttpServletResponse response, @RequestBody CustomerUpdateRequest customerUpdateRequest) {
        int newId = customerRepository.updateCustomer(customerUpdateRequest);
        return newId;
    }

    @PostMapping("/customerRepeat")
    public void createCustomer2(@Valid @RequestBody CustomerCreateRequest customerCreateRequest) {
        ExecutorService executor = Executors.newFixedThreadPool(10);

        Runnable runnableTask = () -> {
            createCustomer(customerCreateRequest);
        };

        for (int i = 0; i < 10; i++) {
            executor.execute(runnableTask);
        }

        executor.shutdown();
    }

    //public CustomerGetResponse getCustomerById(HttpServletResponse response, @PathVariable("id") int id) {

}
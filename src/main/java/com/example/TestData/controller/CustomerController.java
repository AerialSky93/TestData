package com.example.TestData.controller;

import com.example.TestData.event.CustomerPublisher;
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
@CrossOrigin
public class CustomerController {

    private CustomerRepository customerRepository;
    private CustomerPublisher customerPublisher;

    @Autowired
    public CustomerController(CustomerRepository customerRepository, CustomerPublisher customerPublisher) {
        this.customerRepository = customerRepository;
        this.customerPublisher = customerPublisher;
    }

    @GetMapping("/customer/{customerId}")
    public CustomerGetResponse getCustomerById(@PathVariable("customerId") int customerId) {
        CustomerGetResponse customerGetResponse = customerRepository.findByCustomerId(customerId);
        return customerGetResponse;
    }

    @GetMapping("/customer")
    public List<CustomerGetResponse> getAllCustomers() {
        List<CustomerGetResponse> customerGetResponseList = customerRepository.findAll();
        return customerGetResponseList;
    }

    @PostMapping("/customer")
    public int createCustomer(@Valid @RequestBody CustomerCreateRequest customerCreateRequest) {
        int newId  = customerRepository.createCustomerId(customerCreateRequest);
        return newId;
    }

    @PostMapping("/customerpost")
    public int createCustomerpost(@Valid @RequestBody CustomerCreateRequest customerCreateRequest) {
        int newId  = customerRepository.createCustomerId(customerCreateRequest);
        return newId;
    }

    @PutMapping("/customer")
    public int updateCustomer(HttpServletResponse response, @RequestBody CustomerUpdateRequest customerUpdateRequest) {
        int newId = customerRepository.updateCustomer(customerUpdateRequest);
        return newId;
    }

    @PostMapping("/customerRepeat")
    public void createCustomerRepeat(@Valid @RequestBody CustomerCreateRequest customerCreateRequest) {
        ExecutorService executor = Executors.newFixedThreadPool(10);

        Runnable runnableTask = () -> {
            createCustomer(customerCreateRequest);
        };

        for (int i = 0; i < 10; i++) {
            executor.execute(runnableTask);
        }

        executor.shutdown();
    }

    @PostMapping("/customerevent")
    public int createCustomerEvent(@Valid @RequestBody CustomerCreateRequest customerCreateRequest) {
        customerPublisher.publishCustomerCreate(customerCreateRequest);
        return 1;
    }

    @GetMapping("/customer2/{customerId}")
    public CustomerGetResponse getCustomerById2(@PathVariable("customerId") int customerId) {
        CustomerGetResponse customerGetResponse = customerRepository.findByCustomerId(customerId);
        return customerGetResponse;
    }

    //public CustomerGetResponse getCustomerById(HttpServletResponse response, @PathVariable("id") int id) {
}
package com.example.TestData.repository;

import com.example.TestData.request.CustomerCreateRequest;
import com.example.TestData.request.CustomerUpdateRequest;
import com.example.TestData.response.CustomerGetResponse;

import java.util.List;

public interface CustomerRepository {
    CustomerGetResponse findByCustomerId(int customerId);
    List<CustomerGetResponse> findAll();
    int createCustomerId(CustomerCreateRequest customerCreateRequest);
    int updateCustomer(CustomerUpdateRequest customerUpdateRequest);
}

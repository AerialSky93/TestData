package com.example.TestData.repository;

import com.example.TestData.request.CustomerCreateRequest;
import com.example.TestData.request.CustomerUpdateRequest;
import com.example.TestData.response.CustomerGetResponse;

public interface CustomerRepository {
    CustomerGetResponse findByCustomerId(int customerId);
    int createCustomerId(CustomerCreateRequest customerCreateRequest);
    int updateCustomer(CustomerUpdateRequest customerUpdateRequest);
}

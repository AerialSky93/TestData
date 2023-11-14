package com.example.TestData.service;

import com.example.TestData.request.CustomerCreateRequest;
import com.example.TestData.request.CustomerUpdateRequest;
import com.example.TestData.response.CustomerGetResponse;

import java.util.List;

public interface CustomerService {
    CustomerGetResponse findByCustomerIdSvc(int customerId);

}

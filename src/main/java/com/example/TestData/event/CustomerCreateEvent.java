package com.example.TestData.event;

import com.example.TestData.request.CustomerCreateRequest;
import org.springframework.context.ApplicationEvent;

public class CustomerCreateEvent extends ApplicationEvent {
    private CustomerCreateRequest customerCreateRequest;

    public CustomerCreateEvent(Object source, CustomerCreateRequest customerCreateRequest) {
        super(source);
        this.customerCreateRequest = customerCreateRequest;
    }

    public CustomerCreateRequest getCustomerCreateRequest() {
        return customerCreateRequest;
    }
}

package com.example.TestData.event;

import com.example.TestData.request.CustomerCreateRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

@Service
public class CustomerPublisher {
    private ApplicationEventPublisher eventPublisher;

    @Autowired
    public CustomerPublisher(ApplicationEventPublisher eventPublisher) {
        this.eventPublisher = eventPublisher;
    }

    public void publishCustomerCreate(CustomerCreateRequest customerCreateRequest) {
        eventPublisher.publishEvent(new CustomerCreateEvent(this, customerCreateRequest));
    }
}
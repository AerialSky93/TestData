package com.example.TestData.event;

import com.example.TestData.repository.CustomerRepository;
import com.example.TestData.request.CustomerCreateRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;

@Service
public class CustomerListener implements ApplicationListener<CustomerCreateEvent> {

    @Autowired  CustomerRepository customerRepository;

    //@EventListener
    public void onApplicationEvent(CustomerCreateEvent event) {
        customerRepository.createCustomerId(event.getCustomerCreateRequest());
    }
}
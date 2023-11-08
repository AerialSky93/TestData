package com.example.TestData;

import com.example.TestData.controller.CustomerController;
import com.example.TestData.repository.CustomerRepository;
import com.example.TestData.response.CustomerGetResponse;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockHttpServletResponse;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@SpringBootTest
class CustomerControllerTest {

    @Test
    void getCustomerIdReturnsResults() {
        CustomerRepository customerRepository = Mockito.mock(CustomerRepository.class);
        CustomerGetResponse expectedCustomerGetResponse = new CustomerGetResponse();
        when(customerRepository.findByCustomerId(1)).thenReturn(expectedCustomerGetResponse);
        CustomerController customerController = new CustomerController(customerRepository);

        //MockHttpServletResponse response = new MockHttpServletResponse();
        CustomerGetResponse actualCustomerGetResponse = customerController.getCustomerById(1);

        assertEquals(expectedCustomerGetResponse, actualCustomerGetResponse);
    }

}
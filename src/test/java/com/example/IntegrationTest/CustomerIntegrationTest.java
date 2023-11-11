package com.example.IntegrationTest;

import com.example.TestData.TestDataApplication;
import com.example.TestData.response.CustomerGetResponse;
import org.json.JSONException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;


@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = TestDataApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class CustomerIntegrationTest {
    @LocalServerPort
    private int port;

    TestRestTemplate restTemplate = new TestRestTemplate();
    HttpHeaders headers = new HttpHeaders();

    private String getURLWithPort(String uri) {
        return "http://localhost:" + port + uri;
    }

    @Test
    public void testRetrieveStudentCourse() throws JSONException {

        HttpEntity<String> entity = new HttpEntity<>(null, headers);

        ResponseEntity<CustomerGetResponse> response = restTemplate.exchange(
                getURLWithPort("/api/customer/1"),
                HttpMethod.GET, entity, CustomerGetResponse.class);

        assertNotNull(response.getBody());
        //JSONAssert.assertEquals(expected, response.getBody(), false);
    }

}
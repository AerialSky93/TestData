package com.example.TestData.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.OffsetDateTime;

import com.example.TestData.response.CustomerGetResponse;
import org.springframework.jdbc.core.RowMapper;

public class CustomerGetMapper implements RowMapper<CustomerGetResponse> {
    public CustomerGetResponse mapRow(ResultSet rs, int rowNum) throws SQLException {
        CustomerGetResponse customer = new CustomerGetResponse();
        customer.setCustomerId(rs.getInt("CustomerId"));
        customer.setFirstName(rs.getString("FirstName"));
        customer.setFeeAmount(rs.getBigDecimal("FeeAmount"));
        customer.setActiveFlag(rs.getBoolean("ActiveFlag"));
        customer.setEnrollmentDate(rs.getObject("EnrollmentDate", OffsetDateTime.class));
        return customer;
    }
}
package com.example.TestData.repository;


import com.example.TestData.mapper.CustomerGetMapper;
import com.example.TestData.request.CustomerCreateRequest;
import com.example.TestData.request.CustomerUpdateRequest;
import com.example.TestData.response.CustomerGetResponse;
import com.example.TestData.status.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.dao.IncorrectResultSizeDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.stereotype.Repository;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;
import org.springframework.transaction.support.TransactionTemplate;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class CustomerRepositoryImpl implements CustomerRepository {

    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    private PlatformTransactionManager transactionManager;

    private TransactionTemplate transactionTemplate;

    BeanPropertyRowMapper<CustomerGetResponse> mapper;

    @Autowired
    public CustomerRepositoryImpl(NamedParameterJdbcTemplate namedParameterJdbcTemplate,
                                  PlatformTransactionManager transactionManager) {
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
        this.transactionManager = transactionManager;
        this.transactionTemplate = new TransactionTemplate(transactionManager);
        mapper = new BeanPropertyRowMapper<>(CustomerGetResponse.class);
        mapper.setPrimitivesDefaultedForNullValue(true);
    }

    @Cacheable("customers")
    public CustomerGetResponse findByCustomerId(int customerId) {
        Map<String, Object> params = new HashMap<>();
        params.put("customerId", customerId);

        String findByCustomerIdSql = """ 
            SELECT customerId, firstName, employeeId, feeAmount, enrollmentDate, activeFlag 
            FROM dbo.customer 
            WHERE customerId = :customerId
        """;

        try {
            //CustomerGetResponse customerGetResponse = namedParameterJdbcTemplate.queryForObject(findByCustomerIdSql, params, mapper);
            CustomerGetResponse customerGetResponse = namedParameterJdbcTemplate.queryForObject(findByCustomerIdSql, params, new CustomerGetMapper());
            return customerGetResponse;
        } catch (IncorrectResultSizeDataAccessException ex) {
            throw new ResourceNotFoundException("Could Not locate Customer id data");
        }
    }

    public List<CustomerGetResponse> findAll() {
        String findAllSql = """ 
            SELECT customerId, firstName, employeeId, feeAmount, enrollmentDate, activeFlag 
            FROM dbo.customer 
        """;

        try {
            List<CustomerGetResponse> customerGetResponseList = namedParameterJdbcTemplate.query(findAllSql, mapper);
            return customerGetResponseList;
        } catch (IncorrectResultSizeDataAccessException ex) {
            throw new ResourceNotFoundException("Could Not get Customer id data");
        }
    }

    public int createCustomerId(CustomerCreateRequest customerCreateRequest) {
        DefaultTransactionDefinition definition = new DefaultTransactionDefinition();
        definition.setIsolationLevel(TransactionDefinition.ISOLATION_READ_COMMITTED);
        definition.setTimeout(3);
        TransactionStatus status = transactionManager.getTransaction(definition);

        String insertSql = """ 
            INSERT INTO dbo.customer (firstName, employeeId, feeAmount, enrollmentDate, activeFlag) 
            VALUES( :firstName , :employeeId, :feeAmount,  :enrollmentDate, :activeFlag)
            """;

        try {
            GeneratedKeyHolder generatedKeyHolder = new GeneratedKeyHolder();

            Map<String, Object> params = new HashMap<>();
            params.put("firstName", customerCreateRequest.getFirstName());
            params.put("employeeId", customerCreateRequest.getEmployeeId());
            params.put("feeAmount", customerCreateRequest.getFeeAmount());
            params.put("enrollmentDate", customerCreateRequest.getEnrollmentDate());
            params.put("activeFlag", customerCreateRequest.isActiveFlag());

            namedParameterJdbcTemplate.update(insertSql, new MapSqlParameterSource(params), generatedKeyHolder);
            transactionManager.commit(status);
            return generatedKeyHolder.getKey().intValue();
        } catch (Exception ex) {
            transactionManager.rollback(status);
            throw ex;
        }
    }
    public int updateCustomer(CustomerUpdateRequest customerUpdateRequest) {
        Map<String, Object> params = new HashMap<>();
        params.put("customerId", customerUpdateRequest.getCustomerId());
        params.put("firstName", customerUpdateRequest.getFirstName());
        params.put("employeeId", customerUpdateRequest.getEmployeeId());
        params.put("feeAmount", customerUpdateRequest.getFeeAmount());
        params.put("enrollmentDate", customerUpdateRequest.getEnrollmentDate());
        String updateSql = """ 
            UPDATE dbo.Customer 
                set firstName= :firstName , 
                employeeId= :employeeId, 
                feeAmount = :feeAmount,
                enrollmentDate = :enrollmentDate
            WHERE customer_Id= :customerId
        """;
        return namedParameterJdbcTemplate.update(updateSql, params);
    }
}


/*
DECLARE @json NVARCHAR(MAX);

SET @json = N'[
  {"id": 2, "info": {"name": "John", "surname": "Smith"}, "age": 25},
  {"id": 5, "info": {"name": "Jane", "surname": "Smith"}, "dob": "2005-11-04T12:00:00"}
]';

SELECT *
FROM OPENJSON(@json) WITH (
    id INT 'strict $.id',
    firstName NVARCHAR(50) '$.info.name',
    lastName NVARCHAR(50) '$.info.surname',
    age INT,
    dateOfBirth DATETIME2 '$.dob'
    );


CREATE TABLE [dbo].[Customer](
	[Customer_Id] [int] IDENTITY(1,1) NOT NULL,
	[FirstName] [varchar](255) NOT NULL,
	[employeeid] [int] NOT NULL,
	[FeeAmount] [decimal](10, 2) NULL,
	[ActiveFlag] [bit] NULL,
	[EnrollmentDate] [datetime] NULL,
	[JSONBlob] [nvarchar](max) NULL,
	CONSTRAINT [PK_Customer] PRIMARY KEY CLUSTERED
	(
		[Customer_Id] ASC
	)
)

CREATE NONCLUSTERED INDEX NC_Customer_EmployeeId ON dbo.Customer(EmployeeId);

ALTER TABLE dbo.Customer ADD CONSTRAINT FK_Customer_EmployeeId FOREIGN KEY (EmployeeId) REFERENCES Employee(Employee_Id);


            List<CustomerGetResponse> customerGetResponseList = namedParameterJdbcTemplate.query(findAllSql,
                    BeanPropertyRowMapper.newInstance(CustomerGetResponse.class));
 */
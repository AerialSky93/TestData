package com.example.TestData.repository;

import com.example.TestData.config.ServerConfiguration;
import com.example.TestData.request.JobBidCreateRequest;
import com.example.TestData.response.CustomerGetResponse;
import com.example.TestData.response.JobBidCreateResponse;
import com.example.TestData.service.JobBidService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;
import org.springframework.transaction.support.TransactionTemplate;

import java.util.HashMap;
import java.util.Map;

@Repository
public class JobBidRepositoryImpl implements JobBidRepository {

    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    private PlatformTransactionManager transactionManager;

    private TransactionTemplate transactionTemplate;

    BeanPropertyRowMapper<CustomerGetResponse> mapper;

    @Autowired
    public JobBidRepositoryImpl(ServerConfiguration serverConfiguration, NamedParameterJdbcTemplate namedParameterJdbcTemplate,
                                    PlatformTransactionManager transactionManager) {
        this.namedParameterJdbcTemplate = serverConfiguration.namedParameterJdbcTemplate();
        this.transactionManager = transactionManager;
        this.transactionTemplate = new TransactionTemplate(transactionManager);
        mapper = new BeanPropertyRowMapper<>(CustomerGetResponse.class);
        mapper.setPrimitivesDefaultedForNullValue(true);
    }


    public JobBidCreateResponse createJobBid(JobBidCreateRequest jobBidCreateRequest) {
        DefaultTransactionDefinition definition = new DefaultTransactionDefinition();
        definition.setIsolationLevel(TransactionDefinition.ISOLATION_READ_COMMITTED);
        TransactionStatus status = transactionManager.getTransaction(definition);

        String insertSql = """ 
            INSERT INTO dbo.JobBid (BidAmount, JobPostingId) 
            VALUES( :bidAmount , :jobPostingId)
            """;

        try {
            GeneratedKeyHolder generatedKeyHolder = new GeneratedKeyHolder();

            Map<String, Object> params = new HashMap<>();
            params.put("bidAmount", jobBidCreateRequest.getBidAmount());
            params.put("jobPostingId", jobBidCreateRequest.getJobPostingId());

            namedParameterJdbcTemplate.update(insertSql, new MapSqlParameterSource(params), generatedKeyHolder);
            transactionManager.commit(status);

            JobBidCreateResponse jobBidCreateResponse = new JobBidCreateResponse();
            int createdValue = generatedKeyHolder.getKey().intValue();
            jobBidCreateResponse.setJobBidId(createdValue);
            return jobBidCreateResponse;
        } catch (Exception ex) {
            transactionManager.rollback(status);
            throw ex;
        }
    }
}

package com.example.TestData.repository;

import com.example.TestData.config.ServerConfiguration;
import com.example.TestData.mapper.CustomerGetMapper;
import com.example.TestData.request.CustomerCreateRequest;
import com.example.TestData.request.JobPostingCreateRequest;
import com.example.TestData.response.CustomerGetResponse;
import com.example.TestData.response.JobPostingCreateResponse;
import com.example.TestData.response.JobPostingRecentGetResponse;
import com.example.TestData.response.JobPostingRecentItem;
import com.example.TestData.status.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.dao.IncorrectResultSizeDataAccessException;
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
import java.util.List;
import java.util.Map;

@Repository
public class JobPostingRepositoryImpl implements JobPostingRepository {
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    private PlatformTransactionManager transactionManager;

    private TransactionTemplate transactionTemplate;

    BeanPropertyRowMapper<CustomerGetResponse> mapper;

    @Autowired
    public JobPostingRepositoryImpl(ServerConfiguration serverConfiguration, NamedParameterJdbcTemplate namedParameterJdbcTemplate,
                                  PlatformTransactionManager transactionManager) {
        this.namedParameterJdbcTemplate = serverConfiguration.namedParameterJdbcTemplate();
        this.transactionManager = transactionManager;
        this.transactionTemplate = new TransactionTemplate(transactionManager);
        mapper = new BeanPropertyRowMapper<>(CustomerGetResponse.class);
        mapper.setPrimitivesDefaultedForNullValue(true);
    }


    public JobPostingCreateResponse createJobPosting(JobPostingCreateRequest jobPostingCreateRequest) {
        DefaultTransactionDefinition definition = new DefaultTransactionDefinition();
        definition.setIsolationLevel(TransactionDefinition.ISOLATION_READ_COMMITTED);
        TransactionStatus status = transactionManager.getTransaction(definition);

        String insertSql = """ 
            INSERT INTO dbo.JobPosting (JobDescription, JobRequirements, PosterLastName, ContactInfo) 
            VALUES( :jobDescription , :jobRequirements, :posterLastName,  :contactInfo)
            """;

        try {
            GeneratedKeyHolder generatedKeyHolder = new GeneratedKeyHolder();

            Map<String, Object> params = new HashMap<>();
            params.put("jobDescription", jobPostingCreateRequest.getJobDescription());
            params.put("jobRequirements", jobPostingCreateRequest.getJobRequirements());
            params.put("posterLastName", jobPostingCreateRequest.getPosterLastName());
            params.put("contactInfo", jobPostingCreateRequest.getContactPhone());

            namedParameterJdbcTemplate.update(insertSql, new MapSqlParameterSource(params), generatedKeyHolder);
            transactionManager.commit(status);

            JobPostingCreateResponse jobPostingCreateResponse = new JobPostingCreateResponse();
            int createdValue = generatedKeyHolder.getKey().intValue();
            jobPostingCreateResponse.setJobPostingId(createdValue);
            return jobPostingCreateResponse;
        } catch (Exception ex) {
            transactionManager.rollback(status);
            throw ex;
        }
    }

    public JobPostingRecentGetResponse getMostRecentJobPostings() {
        String findAllSql = """ 
            SELECT TOP (10) JobPostingId
                  ,JobDescription
                  ,JobRequirements
                  ,PosterLastName
                  ,ContactInfo
                  ,PostingDate
              FROM dbo.JobPosting
              order by PostingDate desc
        """;

        try {
            List<JobPostingRecentItem> jobPostingRecentItems = namedParameterJdbcTemplate.query(findAllSql, BeanPropertyRowMapper.newInstance(JobPostingRecentItem.class));
            JobPostingRecentGetResponse jobPostingRecentGetResponse = new JobPostingRecentGetResponse();
            jobPostingRecentGetResponse.setJobPostingRecentResponse(jobPostingRecentItems);
            return jobPostingRecentGetResponse;
        } catch (IncorrectResultSizeDataAccessException ex) {
            throw new ResourceNotFoundException("Could Not get Customer id data");
        }
    }

}

/*

DROP TABLE [dbo].JobPosting


DROP TABLE [dbo].JobPosting
CREATE TABLE [dbo].JobPosting(
	JobPostingId [int] IDENTITY(1,1) NOT NULL,
	JobDescription [varchar](255) NULL,
	JobRequirements [varchar](255) NULL,
	PosterLastName [varchar](255) NULL,
	ContactInfo varchar(255),
	PostingDate [datetimeoffset](7) DEFAULT GETDATE() NULL,
	CONSTRAINT [PK_JobPosting] PRIMARY KEY CLUSTERED
	(
		JobPostingId ASC
	)
)

 */
package com.example.TestData.config;


import com.microsoft.sqlserver.jdbc.SQLServerDataSource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

import javax.sql.DataSource;

@Configuration
public class ServerConfiguration {

    @Value("${apiKey}")
    private String apiKey;
    @Bean
    public String getApiKey() {
        return apiKey;
    }

    @Bean
    public DataSource getDataSource() {
        SQLServerDataSource ds = new SQLServerDataSource();

        ds.setServerName("test-data-server.database.windows.net");
        //ds.setServerName("localhost");
        ds.setUser("sauser");
        ds.setPassword("sapwd12345*");
        ds.setPortNumber(1433);
        ds.setTrustServerCertificate(true);
        ds.setDatabaseName("test");
        return ds;
    }

    @Bean
    public NamedParameterJdbcTemplate namedParameterJdbcTemplate() {
        return new NamedParameterJdbcTemplate(getDataSource());
    }
}

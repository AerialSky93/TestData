package com.example.TestData.config;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.BeanPropertyRowMapper;

@Configuration
public class ServerConfiguration {

    // Passing the key which you set in application.properties
    @Value("${apiKey}")
    private String apiKey;


    // Getting the value from that key which
    // you set in application.properties
    @Bean
    public String getApiKey() {
        return apiKey;
    }


    @Bean
    public BeanPropertyRowMapper beanPropertyRowMapper() {
        return new BeanPropertyRowMapper();
    }

/*    @Bean
    public NamedParameterJdbcTemplate namedParameterJdbcTemplate() {
        return new NamedParameterJdbcTemplate(new DataSource());
    }*/
}

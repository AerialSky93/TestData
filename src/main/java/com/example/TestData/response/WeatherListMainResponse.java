package com.example.TestData.response;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class WeatherListMainResponse {
    private BigDecimal temp;
    private BigDecimal temp_min;
}

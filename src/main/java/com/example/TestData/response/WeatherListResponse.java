package com.example.TestData.response;

import lombok.Data;

import java.util.List;

@Data
public class WeatherListResponse {
    private int dt;
    private WeatherListMainResponse main;
}

package com.example.TestData.response;

import lombok.Data;

import java.util.List;

@Data
public class WeatherGetResponse {
    private String cod;
    private String message;
    private int cnt;
    private List<WeatherListResponse> list;
}

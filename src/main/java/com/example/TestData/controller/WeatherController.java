package com.example.TestData.controller;


import com.example.TestData.config.ServerConfiguration;
import com.example.TestData.response.WeatherGetResponse;
import com.example.TestData.response.WeatherListResponse;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api")
public class WeatherController {

    private ServerConfiguration serverConfiguration;
    private String uri;

    ObjectMapper objectMapper = new ObjectMapper();

    @Autowired
    public WeatherController(ServerConfiguration serverConfiguration) {
        this.serverConfiguration = serverConfiguration;
        this.uri = "http://api.openweathermap.org/data/2.5/forecast?id=524901&appid=" + serverConfiguration.getApiKey();
    }

    @GetMapping("/weather")
    public Object getWeather(HttpServletResponse response) throws JsonProcessingException {

        ObjectMapper mapper = new ObjectMapper();
        String newString = "{\"nick\": \"cowtowncoder\"}";
        JsonNode newNode = mapper.readTree(newString);

        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<Object> apiResult = restTemplate.exchange(
                uri, HttpMethod.GET, null,
                new ParameterizedTypeReference<>(){});

        ResponseEntity<WeatherGetResponse> apiResult2 = restTemplate.getForEntity(uri, WeatherGetResponse.class);

        //WeatherGetResponse apiResult3 = restTemplate.getForObject(uri, WeatherGetResponse.class);

        Object result = apiResult.getBody();
        WeatherGetResponse result2 = apiResult2.getBody();

        List<WeatherListResponse> resultFilter = result2.getList().stream().filter(x->x.getDt() == 1699488000).collect(Collectors.toList());

        if (result == null) {
            response.setStatus(HttpStatus.NO_CONTENT.value());
        }
        return result;
    }

    @GetMapping("weather/multiple")
    public void getWeatherMultiple() throws ExecutionException, InterruptedException {
        CompletableFuture<Object> weatherFuture = CompletableFuture.supplyAsync(() -> getWeather());
        CompletableFuture<Object> accountsFuture = CompletableFuture.supplyAsync(() -> getAccounts());
        CompletableFuture.allOf(weatherFuture, accountsFuture).join();

        Object weather = weatherFuture.get();
        Object accounts = accountsFuture.get();
        int x = 5;
    }

    private Object getWeather() {
        RestTemplate restTemplate = new RestTemplate();
        return restTemplate.getForEntity(uri, WeatherGetResponse.class);
    }

    private Object getAccounts() {
        RestTemplate restTemplate = new RestTemplate();
        return restTemplate.getForEntity("https://hackcheck.woventeams.com/api/v4/breachedaccount/test@example.com", Object.class);
    }
}

// https://stackoverflow.com/questions/51464116/filter-and-collect-nested-collection-elements-using-java-8-stream
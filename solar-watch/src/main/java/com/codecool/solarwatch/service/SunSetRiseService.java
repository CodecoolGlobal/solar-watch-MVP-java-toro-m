package com.codecool.solarwatch.service;

import com.codecool.solarwatch.model.Coordinates;
import com.codecool.solarwatch.model.SunSetRiseReport;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDate;

@Service
public class SunSetRiseService {
    // todo get this off github
    private static final String API_KEY = "3e0cd5534393786af5b3cf1eccf2f97b";

    private final RestTemplate restTemplate;

    public SunSetRiseService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public SunSetRiseReport getSunSetRise(String location, LocalDate date) {
        String coordinatesFromLocationUrl = String.format("http://api.openweathermap.org/geo/1.0/direct?q=%s&appid=%s", location, API_KEY);
        Coordinates coordinates = restTemplate.getForObject(coordinatesFromLocationUrl, Coordinates.class);



    }
}

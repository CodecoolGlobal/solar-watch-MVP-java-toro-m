package com.codecool.solarwatch.service;

import com.codecool.solarwatch.model.Coordinates;
import com.codecool.solarwatch.model.SunSetRiseReport;
import com.codecool.solarwatch.model.SunSetRiseTimes;
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
        String coordinatesFromLocationUrl = String.format(
                "http://api.openweathermap.org/geo/1.0/direct?q=%s&appid=%s", location, API_KEY
        );

        Coordinates[] coordinatesArray = restTemplate.getForObject(coordinatesFromLocationUrl, Coordinates[].class);
        if (coordinatesArray == null || coordinatesArray.length == 0) {
            throw new RuntimeException("Could not find coordinates for location: " + location);
        }
        Coordinates firstCoordinate = coordinatesArray[0];

        String sunSetRiseUrl = String.format(
                "https://api.sunrise-sunset.org/json?lat=%s&lng=%s&date=%s",
                firstCoordinate.lat(), firstCoordinate.lon(), date
        );

        SunSetRiseTimes sunTimes = restTemplate.getForObject(sunSetRiseUrl, SunSetRiseTimes.class);

        return new SunSetRiseReport(
                location,
                date,
                sunTimes.sunRise(),
                sunTimes.sunSet(),
                sunTimes.tzid()
        );
    }

}

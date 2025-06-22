package com.codecool.solarwatch.service;

import com.codecool.solarwatch.model.Coordinates;
import com.codecool.solarwatch.model.SunSetRiseReport;
import com.codecool.solarwatch.model.SunSetRiseTimes;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDate;

@Service
public class SunSetRiseService {
    // todo get this off github
    String API_KEY = System.getenv("API_KEY");
    private final RestTemplate restTemplate;

    public SunSetRiseService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public SunSetRiseReport getSunSetRise(String location, LocalDate date) {
        try {
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
        } catch (RestClientException e) {
            throw new RuntimeException("External API call failed: " + e.getMessage(), e);
        }
    }


}

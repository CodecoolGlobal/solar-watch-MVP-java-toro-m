package com.codecool.solarwatch.service;

import com.codecool.solarwatch.model.*;
import com.codecool.solarwatch.repository.CityRepository;
import com.codecool.solarwatch.repository.SunSetRiseTimesRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;
import org.springframework.web.reactive.function.client.WebClient;

import java.time.LocalDate;
import java.util.Optional;

@Service
public class SunSetRiseService {
    String API_KEY = System.getenv("API_KEY");
    private final WebClient webClient;
    private final CityRepository cityRepository;
    private final SunSetRiseTimesRepository sunSetRiseTimesRepository;

    public SunSetRiseService(WebClient webClient, CityRepository cityRepository, SunSetRiseTimesRepository sunSetRiseTimesRepository) {
        this.webClient = webClient;
        this.cityRepository = cityRepository;
        this.sunSetRiseTimesRepository = sunSetRiseTimesRepository;
    }

    private City getCityFromExternalApi(String location) {
        try {
            String coordinatesFromLocationUrl = String.format(
                    "http://api.openweathermap.org/geo/1.0/direct?q=%s&appid=%s", location, API_KEY
            );

            Coordinates[] coordinatesArray = webClient
                    .get()
                    .uri(coordinatesFromLocationUrl)
                    .retrieve()
                    .bodyToMono(Coordinates[].class)
                    .block();

            if (coordinatesArray == null || coordinatesArray.length == 0) {
                throw new RuntimeException("Could not find coordinates for location: " + location);
            }
            Coordinates firstCoordinate = coordinatesArray[0];
            return new City(location, firstCoordinate.lon(), firstCoordinate.lat());

        } catch (RestClientException e) {
            throw new RuntimeException("External API call failed: " + e.getMessage(), e);
        }
    }

    private City getCity(String location) {
        Optional<City> databaseCity = cityRepository.findByName(location);
        City city;
        if (databaseCity.isEmpty()) {
            city = getCityFromExternalApi(location);
            cityRepository.save(city);
        } else {
            city = databaseCity.get();
        }
        return city;
    }

    private SunSetRiseTimesData getSunSetRiseTimesFromExternalApi(City city, LocalDate date) {
        try {

            String sunSetRiseUrl = String.format(
                    "https://api.sunrise-sunset.org/json?lat=%s&lng=%s&date=%s",
                    city.getLatitude(), city.getLongitude(), date
            );

            SunSetRiseTimes sunTimes = WebClient.create()
                    .get().uri(sunSetRiseUrl)
                    .retrieve()
                    .bodyToMono(SunSetRiseTimes.class)
                    .block();

            return new SunSetRiseTimesData(city, date, sunTimes.sunRise(), sunTimes.sunSet(), sunTimes.tzid());
        } catch (RestClientException e) {
            throw new RuntimeException("External API call failed: " + e.getMessage(), e);
        }
    }


    private SunSetRiseTimesData getSunSetRiseTimes(City city, LocalDate date) {
        SunSetRiseTimesData sunSetRiseTimesData;
        Optional<SunSetRiseTimesData> databaseReport = sunSetRiseTimesRepository.findByCityAndDate(city, date);
        if (databaseReport.isEmpty()) {
            sunSetRiseTimesData = getSunSetRiseTimesFromExternalApi(city, date);
            sunSetRiseTimesRepository.save(sunSetRiseTimesData);
        } else {
            sunSetRiseTimesData = databaseReport.get();
        }
        return sunSetRiseTimesData;
    }

    @Transactional
    public SunSetRiseTimesData getSunSetRise(String location, LocalDate date) {
        City city = getCity(location);
        SunSetRiseTimesData data = getSunSetRiseTimes(city, date);
        return data;

    }


}

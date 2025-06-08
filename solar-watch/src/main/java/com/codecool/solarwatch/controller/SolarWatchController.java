package com.codecool.solarwatch.controller;

import org.springframework.http.RequestEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDate;

public class SolarWatchController {
    private static final String DEFAULT_LOCATION="Budapest";


    @GetMapping("/sun")
public RequestEntity getSunriseAndSunset(@RequestParam(required = false) String location, @RequestParam(required = false) LocalDate date) {
if (location == null || location.equals("")) {
    location = DEFAULT_LOCATION;
}
if (date == null) {
    date = LocalDate.now();
}
//todo implement service and continue

    }


}

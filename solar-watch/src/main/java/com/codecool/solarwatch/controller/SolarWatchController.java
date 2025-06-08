package com.codecool.solarwatch.controller;

import com.codecool.solarwatch.model.SunSetRiseReport;
import com.codecool.solarwatch.service.SunSetRiseService;
import org.springframework.http.RequestEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDate;

public class SolarWatchController {
    private static final String DEFAULT_LOCATION="Budapest";
    private SunSetRiseService sunSetRiseService;

    public SolarWatchController(SunSetRiseService sunSetRiseService) {
        this.sunSetRiseService = sunSetRiseService;
    }

    @GetMapping("/sun")
public SunSetRiseReport getSunriseAndSunset(@RequestParam(required = false) String location, @RequestParam(required = false) LocalDate date) {
if (location == null || location.equals("")) {
    location = DEFAULT_LOCATION;
}
if (date == null) {
    date = LocalDate.now();
}
return sunSetRiseService.getSunSetRise(location, date);

    }


}

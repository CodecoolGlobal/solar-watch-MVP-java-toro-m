package com.codecool.solarwatch.controller;

import com.codecool.solarwatch.model.SunSetRiseReport;
import com.codecool.solarwatch.model.SunSetRiseTimes;
import com.codecool.solarwatch.model.SunSetRiseTimesData;
import com.codecool.solarwatch.service.SunSetRiseService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;

@RestController
@RequestMapping("/api")
public class SolarWatchController {
    private static final String DEFAULT_LOCATION = "Budapest";
    private SunSetRiseService sunSetRiseService;

    public SolarWatchController(SunSetRiseService sunSetRiseService) {
        this.sunSetRiseService = sunSetRiseService;
    }

    @GetMapping("/sun")
    public SunSetRiseTimesData getSunriseAndSunset(@RequestParam(required = false) String location, @RequestParam(required = false) LocalDate date) {
        if (location == null || location.equals("")) {
            location = DEFAULT_LOCATION;
        }
        if (date == null) {
            date = LocalDate.now();
        }
        location = location.toLowerCase();
        return sunSetRiseService.getSunSetRise(location, date);

    }


}

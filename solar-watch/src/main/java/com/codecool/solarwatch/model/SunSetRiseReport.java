package com.codecool.solarwatch.model;

import java.time.LocalDate;
import java.time.LocalTime;

public record SunSetRiseReport(LocalDate date, String location, LocalTime sunRise, LocalTime sunSet) {
}

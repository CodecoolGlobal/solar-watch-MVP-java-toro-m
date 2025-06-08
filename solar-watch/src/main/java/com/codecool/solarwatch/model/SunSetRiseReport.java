package com.codecool.solarwatch.model;

import java.time.LocalDate;

public record SunSetRiseReport(String locationName, LocalDate date, String sunRise, String sunSet, String timeZoneId) {
}

package com.codecool.solarwatch.model;

import java.time.LocalDate;

public record SunSetRiseReport(String locationName, LocalDate date, double latitude, double longitude, String timeZoneId) {
}

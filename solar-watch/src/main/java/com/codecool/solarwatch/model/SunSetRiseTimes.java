package com.codecool.solarwatch.model;

import com.codecool.solarwatch.exeptions.InvalidSunTimeFormatException;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Locale;

public record SunSetRiseTimes(String sunrise, String sunset, String tzid) {

    private static final DateTimeFormatter TIME_FORMATTER =
            DateTimeFormatter.ofPattern("h:mm:ss a", Locale.US);

    public LocalTime sunriseTime() {
        try {
            return LocalTime.parse(sunrise, TIME_FORMATTER);
        } catch (DateTimeParseException e) {
            throw new InvalidSunTimeFormatException("Failed to parse sunrise time: " + sunrise);
        }
    }

    public LocalTime sunsetTime() {
        try {
            return LocalTime.parse(sunset, TIME_FORMATTER);
        } catch (DateTimeParseException e) {
            throw new InvalidSunTimeFormatException("Failed to parse sunset time: " + sunset);
        }
    }
}

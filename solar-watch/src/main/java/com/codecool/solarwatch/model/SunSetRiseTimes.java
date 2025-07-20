package com.codecool.solarwatch.model;

import com.codecool.solarwatch.exeptions.InvalidSunTimeFormatException;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Locale;

public record SunSetRiseTimes(SunSetRiseResults results, String tzid) {

    private static final DateTimeFormatter TIME_FORMATTER =
            DateTimeFormatter.ofPattern("h:mm:ss a", Locale.US);

    public String sunRise() {
        return results.sunrise();
    }

    public String sunSet() {
        return results.sunset();
    }

    public LocalTime sunRiseTime() {
        try {
            return LocalTime.parse(results.sunrise(), TIME_FORMATTER);
        } catch (DateTimeParseException e) {
            throw new InvalidSunTimeFormatException();
        }
    }

    public LocalTime sunSetTime() {
        try {
            return LocalTime.parse(results.sunset(), TIME_FORMATTER);
        } catch (DateTimeParseException e) {
            throw new InvalidSunTimeFormatException();
        }
    }
}

package com.codecool.solarwatch.model;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
public class SunSetRiseTimesData {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne
    @JoinColumn(name = "city_id")
    private City city;

    private LocalDate date;
    private String sunriseTime;
    private String sunsetTime;
    private String timezoneId;

    public SunSetRiseTimesData(City city, LocalDate date, String sunriseTime, String sunsetTime, String timezoneId) {
        this.city = city;
        this.date = date;
        this.sunriseTime = sunriseTime;
        this.sunsetTime = sunsetTime;
        this.timezoneId = timezoneId;
    }

    public SunSetRiseTimesData() {
    }

    public long getId() {
        return id;
    }

    public City getCity() {
        return city;
    }

    public LocalDate getDate() {
        return date;
    }

    public String getSunriseTime() {
        return sunriseTime;
    }

    public String getSunsetTime() {
        return sunsetTime;
    }

    public String getTimezoneId() {
        return timezoneId;
    }
}

package com.codecool.solarwatch.model;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
public class SunSetRiseTimesData {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne
    @JoinColumn(name = "city_id", referencedColumnName = "id")
    private City city;

    private LocalDate date;
    private String sunriseTime;
    private String sunsetTime;
    private String timezoneId;


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

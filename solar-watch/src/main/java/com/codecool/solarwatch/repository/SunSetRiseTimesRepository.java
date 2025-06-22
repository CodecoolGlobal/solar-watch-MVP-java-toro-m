package com.codecool.solarwatch.repository;

import com.codecool.solarwatch.model.City;
import com.codecool.solarwatch.model.SunSetRiseTimesData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.Optional;

@Repository
public interface SunSetRiseTimesRepository extends JpaRepository<SunSetRiseTimesData, Long> {

    Optional<SunSetRiseTimesData> findByCityAndDate(City city, LocalDate date);

}

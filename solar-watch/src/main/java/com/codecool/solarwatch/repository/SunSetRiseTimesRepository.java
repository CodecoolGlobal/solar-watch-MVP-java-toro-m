package com.codecool.solarwatch.repository;

import com.codecool.solarwatch.model.SunSetRiseTimesData;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SunSetRiseTimesRepository extends JpaRepository<SunSetRiseTimesData, Long> {
}

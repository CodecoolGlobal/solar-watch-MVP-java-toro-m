package com.codecool.solarwatch.repository;

import com.codecool.solarwatch.model.SunSetRiseTimesData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SunSetRiseTimesRepository extends JpaRepository<SunSetRiseTimesData, Long> {
}

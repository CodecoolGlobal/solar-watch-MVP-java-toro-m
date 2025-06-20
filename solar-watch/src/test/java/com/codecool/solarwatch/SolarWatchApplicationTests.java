package com.codecool.solarwatch;import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

import java.time.LocalDate;

import com.codecool.solarwatch.model.Coordinates;
import com.codecool.solarwatch.model.SunSetRiseReport;
import com.codecool.solarwatch.model.SunSetRiseResults;
import com.codecool.solarwatch.model.SunSetRiseTimes;
import com.codecool.solarwatch.service.SunSetRiseService;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

public class SolarWatchApplicationTests {

	private SunSetRiseService sunSetRiseService;

	@Mock
	private RestTemplate restTemplate;

	@BeforeEach
	void setup() {
		MockitoAnnotations.openMocks(this);
		sunSetRiseService = new SunSetRiseService(restTemplate);
	}

	@Test
	void testGetSunSetRise_success() {
		String location = "Budapest";
		LocalDate date = LocalDate.of(2025, 6, 9);

		Coordinates[] coords = new Coordinates[] {
				new Coordinates(location, 47.4979, 19.0403) // lat, lon
		};

		SunSetRiseResults results= new SunSetRiseResults("2:47:33 AM", "6:38:41 PM");
		SunSetRiseTimes times = new SunSetRiseTimes(results, "UTC");

		when(restTemplate.getForObject(
				contains("geo/1.0/direct"), eq(Coordinates[].class)))
				.thenReturn(coords);

		when(restTemplate.getForObject(
				contains("sunrise-sunset.org/json"), eq(SunSetRiseTimes.class)))
				.thenReturn(times);

		SunSetRiseReport report = sunSetRiseService.getSunSetRise(location, date);

		assertNotNull(report);
		assertEquals(location, report.locationName());
		assertEquals(date, report.date());
		assertEquals("2:47:33 AM", report.sunRise());
		assertEquals("6:38:41 PM", report.sunSet());
		assertEquals("UTC", report.timeZoneId());
	}

	@Test
	void testGetSunSetRise_coordinatesNotFound() {
		String location = "UnknownPlace";
		LocalDate date = LocalDate.now();

		when(restTemplate.getForObject(anyString(), eq(Coordinates[].class)))
				.thenReturn(new Coordinates[0]);

		RuntimeException ex = assertThrows(RuntimeException.class, () -> {
			sunSetRiseService.getSunSetRise(location, date);
		});

		assertTrue(ex.getMessage().contains("Could not find coordinates"));
	}

	@Test
	void testGetSunSetRise_restClientException() {
		String location = "Budapest";
		LocalDate date = LocalDate.now();

		when(restTemplate.getForObject(anyString(), eq(Coordinates[].class)))
				.thenThrow(new RestClientException("API unavailable"));

		RuntimeException ex = assertThrows(RuntimeException.class, () -> {
			sunSetRiseService.getSunSetRise(location, date);
		});

		assertTrue(ex.getMessage().contains("External API call failed"));
	}
}

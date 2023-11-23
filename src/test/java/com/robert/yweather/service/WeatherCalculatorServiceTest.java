package com.robert.yweather.service;

import com.robert.yweather.client.model.NullYonderWeather;
import com.robert.yweather.client.model.YonderForecast;
import com.robert.yweather.client.model.YonderWeather;
import com.robert.yweather.model.Weather;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.Collections;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
class WeatherCalculatorServiceTest {
    private static final String CITY_NAME = "cityName";
    @InjectMocks
    WeatherCalculatorServiceImpl weatherCalculatorService;
    @Test
    void calculateWeather_full() {
        YonderForecast yonderForecast1 = YonderForecast.builder()
                .day(1)
                .temperature(10)
                .wind(10)
                .build();
        YonderForecast yonderForecast2 = YonderForecast.builder()
                .day(2)
                .temperature(10)
                .wind(10)
                .build();
        YonderWeather yonderWeather = YonderWeather.builder()
                .temperature(10)
                .wind(10)
                .description("description")
                .forecast(Arrays.asList(yonderForecast1, yonderForecast2))
                .build();

        Weather result = weatherCalculatorService.calculateWeather(yonderWeather, CITY_NAME);

        assertEquals(CITY_NAME, result.getName());
        assertEquals("10.0", result.getTemperature());
        assertEquals("10.0", result.getWind());
    }

    @Test
    void calculateWeather_withoutForcast() {
        YonderWeather yonderWeather = YonderWeather.builder()
                .temperature(10)
                .wind(10)
                .description("description")
                .forecast(Collections.emptyList())
                .build();

        Weather result = weatherCalculatorService.calculateWeather(yonderWeather, CITY_NAME);

        assertEquals(CITY_NAME, result.getName());
        assertEquals("10.0", result.getTemperature());
        assertEquals("10.0", result.getWind());
    }

    @Test
    void calculateWeather_withNullYonderWeather() {
        Weather result = weatherCalculatorService.calculateWeather(new NullYonderWeather(), CITY_NAME);

        assertEquals(CITY_NAME, result.getName());
        assertEquals("", result.getTemperature());
        assertEquals("", result.getWind());
    }
}
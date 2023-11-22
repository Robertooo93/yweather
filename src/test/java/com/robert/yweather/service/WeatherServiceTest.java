package com.robert.yweather.service;

import com.robert.yweather.client.YonderWeatherService;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import static org.junit.jupiter.api.Assertions.*;

class WeatherServiceTest {
    @Mock
    YonderWeatherService yonderWeatherService;
    @InjectMocks
    WeatherService weatherService;
    @Test
    public void test() {

    }
}
package com.robert.yweather.service;

import com.robert.yweather.client.YonderWeatherService;
import com.robert.yweather.client.model.YonderWeather;
import com.robert.yweather.model.Weather;
import com.robert.yweather.property.SupportedCities;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class WeatherServiceTest {
    @Mock
    YonderWeatherService yonderWeatherService;
    @Mock
    WeatherCalculatorService weatherCalculatorService;
    @Mock
    SupportedCities supportedCities;
    @InjectMocks
    WeatherServiceImpl weatherService;

    @Test
    public void calculateWeather_cityFilterInAction() {
        List<String> inputCities = Arrays.asList("City1", "City2", "City3");
        Set<String> supportedCitiesSet = new HashSet<>(Arrays.asList("City1", "City2"));
        when(supportedCities.getCity()).thenReturn(supportedCitiesSet);

        // Mock responses from yonderWeatherService
        when(yonderWeatherService.getByCityName("City1")).thenReturn(Mono.just(YonderWeather.builder().build()));
        when(yonderWeatherService.getByCityName("City2")).thenReturn(Mono.just(YonderWeather.builder().build()));

        // Mock responses from weatherCalculatorService
        when(weatherCalculatorService.calculateWeather(YonderWeather.builder().build(), "City1"))
                .thenReturn(Weather.builder()
                        .name("City1")
                        .build());
        when(weatherCalculatorService.calculateWeather(YonderWeather.builder().build(), "City2"))
                .thenReturn(Weather.builder()
                        .name("City2")
                        .build());

        // Test the Flux using StepVerifier
        Flux<Weather> resultFlux = weatherService.calculateWeather(inputCities);

        StepVerifier.create(resultFlux)
                .expectNextCount(2) // Assuming City3 is not supported, we expect two valid results
                .verifyComplete();

        verify(yonderWeatherService, times(2)).getByCityName(anyString());
    }
}
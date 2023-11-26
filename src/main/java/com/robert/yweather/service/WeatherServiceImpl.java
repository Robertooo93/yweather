package com.robert.yweather.service;

import com.robert.yweather.client.YonderWeatherService;
import com.robert.yweather.model.Weather;
import com.robert.yweather.property.SupportedCities;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.util.function.Tuples;

import java.util.Comparator;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class WeatherServiceImpl implements WeatherService {

    private final YonderWeatherService yonderWeatherService;

    private final WeatherCalculatorService weatherCalculatorService;

    private final SupportedCities supportedCities;

    public Flux<Weather> calculateWeather(List<String> cities) {
        Set<String> supportedCities = filterCities(cities);
        Flux<Weather> weatherFluxResult = Flux.empty();
        for (String city : supportedCities) {
            Mono<Weather> weatherMonoResult = yonderWeatherService.getByCityName(city)
                    .map(result -> Tuples.of(city, result))
                    .map(tuple -> weatherCalculatorService.calculateWeather(tuple.getT2(), tuple.getT1())).log();
            weatherFluxResult = Flux.concat(weatherFluxResult, weatherMonoResult);
        }

        return weatherFluxResult
                .sort(Comparator.comparing(Weather::getName));
    }

    private Set<String> filterCities(List<String> cities) {
        return cities.stream()
                .filter(city -> supportedCities.getCity().contains(city))
                .collect(Collectors.toSet());
    }

}

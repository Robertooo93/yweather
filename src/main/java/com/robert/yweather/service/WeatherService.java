package com.robert.yweather.service;

import com.robert.yweather.client.YonderWeatherService;
import com.robert.yweather.model.Weather;
import com.robert.yweather.property.SupportedCities;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.util.function.Tuple2;
import reactor.util.function.Tuples;

import java.util.Comparator;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class WeatherService {

    private final YonderWeatherService yonderWeatherService;

    private final WeatherCalculatorService weatherCalculatorService;

    private final SupportedCities supportedCities;

    public Flux<Weather> calculateWeather(List<String> cities) {
        Set<String> supportedCities = filterCities(cities);
        return Flux.fromIterable(supportedCities)
                .flatMap(path -> yonderWeatherService.getByCityName(path)
                        .map(result -> Tuples.of(path, result)))
                .sort(Comparator.comparing(Tuple2::getT1))
                .map(tuple -> weatherCalculatorService.calculateWeather(tuple.getT2(), tuple.getT1()));
    }

    private Set<String> filterCities(List<String> cities) {
        return cities.stream()
                .filter(city -> supportedCities.getCity().contains(city))
                .collect(Collectors.toSet());
    }

}

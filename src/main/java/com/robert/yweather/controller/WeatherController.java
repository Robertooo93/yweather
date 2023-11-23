package com.robert.yweather.controller;

import com.robert.yweather.model.Weather;
import com.robert.yweather.service.FileService;
import com.robert.yweather.service.WeatherService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

import java.util.List;

@RestController
@RequestMapping("/api/weather")
@RequiredArgsConstructor
public class WeatherController {

    private final WeatherService weatherService;

    private final FileService fileService;

    @GetMapping
    public Flux<Weather> getWeather(@RequestParam("city") List<String> cities) {
        Flux<Weather> weatherFlux = weatherService.calculateWeather(cities);

        fileService.writeToFile(weatherFlux.cache());

        return weatherFlux;
    }

}

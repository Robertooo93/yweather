package com.robert.yweather.service;

import com.robert.yweather.model.Weather;
import reactor.core.publisher.Flux;

import java.util.List;

public interface WeatherService {
    Flux<Weather> calculateWeather(List<String> cities);
}

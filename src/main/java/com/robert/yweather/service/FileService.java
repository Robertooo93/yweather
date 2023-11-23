package com.robert.yweather.service;

import com.robert.yweather.model.Weather;
import reactor.core.publisher.Flux;

public interface FileService {
    void writeToFile(Flux<Weather> weatherFlux);
}

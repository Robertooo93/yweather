package com.robert.yweather.client;

import com.robert.yweather.client.model.NullYonderWeather;
import com.robert.yweather.client.model.YonderWeather;
import com.robert.yweather.client.property.YonderClientProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Service
public class YonderWeatherService {

    private final WebClient webClient;

    @Autowired
    public YonderWeatherService(YonderClientProperties yonderClientProperties) {
        this.webClient = WebClient.create(yonderClientProperties.getHostName());
    }

    public Mono<YonderWeather> getByCityName(String cityName) {
        return webClient.get()
                .uri("/" + cityName)
                .retrieve()
                .onStatus(
                        HttpStatusCode::isError,
                        clientResponse -> Mono.error(new RuntimeException())
                )
                .bodyToMono(YonderWeather.class)
                .onErrorResume(throwable -> Mono.just(new NullYonderWeather()));
    }
}

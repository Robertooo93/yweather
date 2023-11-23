package com.robert.yweather.controller;

import com.robert.yweather.client.YonderWeatherService;
import com.robert.yweather.client.model.YonderWeather;
import com.robert.yweather.model.Weather;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Mono;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@AutoConfigureWebTestClient
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class WeatherControllerTestIT {

    @Autowired
    WebTestClient webTestClient;

    @MockBean
    private YonderWeatherService yonderWeatherService;

    @Test
    public void testGetWeather() {
        when(yonderWeatherService.getByCityName(anyString())).thenReturn(Mono.just(YonderWeather.builder()
                .forecast(Collections.emptyList())
                .build()));

        // Act & Assert
        WebTestClient.ResponseSpec responseSpec = webTestClient
                .get()
                .uri("api/weather?city=Cluj-Napoca")
                .exchange();

        responseSpec.expectStatus().isOk();

        List<Weather> resultList = responseSpec.expectBodyList(Weather.class).returnResult().getResponseBody();
        assert resultList != null;
        assertEquals(1, resultList.size());
    }
}
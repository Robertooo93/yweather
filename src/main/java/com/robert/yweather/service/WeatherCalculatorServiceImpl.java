package com.robert.yweather.service;

import com.robert.yweather.client.model.NullYonderWeather;
import com.robert.yweather.client.model.YonderForecast;
import com.robert.yweather.client.model.YonderWeather;
import com.robert.yweather.model.Weather;
import org.springframework.stereotype.Service;

@Service
public class WeatherCalculatorServiceImpl implements WeatherCalculatorService {
    @Override
    public Weather calculateWeather(YonderWeather yonderWeather, String cityName) {
        if (yonderWeather instanceof NullYonderWeather) {
            return Weather.builder()
                    .name(cityName)
                    .wind("")
                    .temperature("")
                    .build();
        }
        double avgTemp = yonderWeather.getTemperature();
        double avgWind = yonderWeather.getWind();
        for (YonderForecast yonderForecastforecast : yonderWeather.getForecast()) {
            avgTemp += yonderForecastforecast.getTemperature();
            avgWind += yonderForecastforecast.getWind();
        }

        avgTemp /= yonderWeather.getForecast().size()+1;
        avgWind /= yonderWeather.getForecast().size()+1;

        return Weather.builder()
                .name(cityName)
                .wind(String.valueOf(avgWind))
                .temperature(String.valueOf(avgTemp))
                .build();
    }
}

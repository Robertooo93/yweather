package com.robert.yweather.service;

import com.robert.yweather.client.model.YonderWeather;
import com.robert.yweather.model.Weather;

public interface WeatherCalculatorService {
    Weather calculateWeather(YonderWeather yonderWeather, String cityName);
}

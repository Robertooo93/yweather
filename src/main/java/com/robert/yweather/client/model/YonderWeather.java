package com.robert.yweather.client.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class YonderWeather {
    private int temperature;
    private int wind;
    private String description;
    private List<YonderForecast> forecast;
}

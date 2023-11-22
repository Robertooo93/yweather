package com.robert.yweather.client.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;

@Data
@NoArgsConstructor
public class YonderWeather {
    private int temperature;
    private int wind;
    private String description;
    private ArrayList<YonderForecast> forecast;
}

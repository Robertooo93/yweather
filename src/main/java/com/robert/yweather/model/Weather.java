package com.robert.yweather.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Weather {
    private String name;
    private String temperature;
    private String wind;
}

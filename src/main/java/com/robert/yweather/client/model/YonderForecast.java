package com.robert.yweather.client.model;

import lombok.*;

@Data
@Builder
@RequiredArgsConstructor
@AllArgsConstructor
public class YonderForecast {
    private int day;
    private int temperature;
    private int wind;
}

package com.robert.yweather.property;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.Set;

@ConfigurationProperties(prefix = "supported")
@Data
public class SupportedCities {
    Set<String> city;
}

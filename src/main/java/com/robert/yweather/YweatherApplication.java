package com.robert.yweather;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
@ConfigurationPropertiesScan({"com.robert.yweather.client.property", "com.robert.yweather.property"})
public class YweatherApplication {

    public static void main(String[] args) {
        SpringApplication.run(YweatherApplication.class, args);
    }

}

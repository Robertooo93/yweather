package com.robert.yweather.client.property;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "client.yonder")
@Data
public class YonderClientProperties {
    private String hostName;
}

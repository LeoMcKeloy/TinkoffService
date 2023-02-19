package com.pyryanov.tinkoffservice.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Data
@ConfigurationProperties(prefix = "api")
public class ApiProperties {
    private Boolean isSandBoxMode;
}

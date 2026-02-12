package com.example.backProcesamientoStream.config;

import lombok.Getter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Getter
@Configuration
@ConfigurationProperties(prefix = "csv")
public class PropertiesConfig {
    private Integer defaultPage;
    private Integer defaultSize;
    private Boolean autoPaginate;
}

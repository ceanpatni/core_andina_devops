package com.example.backProcesamientoStream;

import com.example.backProcesamientoStream.config.PropertiesConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling  // habilita @Scheduled
@EnableAsync       // habilita ejecución asíncrona
@EnableConfigurationProperties(PropertiesConfig.class)
public class BackProcesamientoStreamApplication {

	public static void main(String[] args) {
		SpringApplication.run(BackProcesamientoStreamApplication.class, args);
	}

}

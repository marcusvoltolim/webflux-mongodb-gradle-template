package com.apirest.webflux;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.mongodb.core.mapping.event.LoggingEventListener;

@SpringBootApplication
public class WebFluxGradleTemplateApplication {

	public static void main(String[] args) {
		SpringApplication.run(WebFluxGradleTemplateApplication.class, args);
	}

	@Bean
	public LoggingEventListener mongoEventListener() {
		return new LoggingEventListener();
	}

}

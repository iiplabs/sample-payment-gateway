package com.iiplabs.spg.web.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

	@Override
	public void addCorsMappings(CorsRegistry registry) {
		registry.addMapping("/api/**")
			.allowedOrigins("http://spg-frontend:5000")
			.allowedMethods("GET", "POST", "PUT", "HEAD", "DELETE", "OPTIONS")
			.allowedHeaders("*")
			.allowCredentials(true);
	}

}

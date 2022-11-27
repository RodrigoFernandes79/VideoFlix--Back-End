package com.rodrigohf.videoFlix;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

@SpringBootApplication
public class VideoflixApplication extends SpringBootServletInitializer {

	

	public static void main(String[] args) {
		SpringApplication.run(VideoflixApplication.class, args);
	}

	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
		
		return builder.sources(VideoflixApplication.class);
	}

	
}
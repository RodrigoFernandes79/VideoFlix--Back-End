package com.rodrigohf.videoFlix.config;

import java.text.ParseException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import com.rodrigohf.videoFlix.services.DataBaseService;

@Configuration
@Profile("test")
public class TestConfig {
	
	@Autowired
	private DataBaseService dataBaseService;
	
	@Bean
	public boolean instanciarDataBaseTestProfile() throws ParseException {
		dataBaseService.instanciarDataBaseTestProfile();
		
		return true;
	}

}

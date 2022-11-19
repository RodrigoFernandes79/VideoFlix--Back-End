package com.rodrigohf.videoFlix.config;

import java.text.ParseException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import com.rodrigohf.videoFlix.services.DataBaseService;

@Configuration
@Profile("dev")
public class DevConfig {
	
	@Autowired
	private DataBaseService dataBaseService;
	
	@Value("${spring.jpa.hibernate.ddl-auto}")
	private String strategy;
	
	@Bean
	public boolean instanciarDataBaseTestProfile() throws ParseException {
		if(!"create".equals(strategy)) {
			return false;
		}
		
		dataBaseService.instanciarDataBaseTestProfile();
		
		return true;
	}

}

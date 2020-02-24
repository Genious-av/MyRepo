package com.annoproject.scanpackages;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
//@ComponentScan("com.annoproject.scanpackages")
@PropertySource("classpath:application.properties")
public class SportConfig {

	
	//define bean for fortune service
	@Bean
	public FortuneService footballFortuneService() {
		return new FootballFortune();
	}
	
	
	//define bean for swim coach and inject dependencies
	
	@Bean
	public Coach footballCoach() {
		return new FootballCoach(footballFortuneService());
	}
	
}

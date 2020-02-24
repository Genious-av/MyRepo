package com.annoproject.scanpackages;

import org.springframework.stereotype.Component;


public class FootballCoach implements Coach {

	FortuneService fortuneService;
	
	
	public FootballCoach(FortuneService thefortuneService) {
		this.fortuneService = thefortuneService;
	}

	@Override
	public String getDailyWorkout() {
		
		return "Find a ball";
	}

	@Override
	public String getDailyFortune() {
		
		return fortuneService.getFortune();
	}

}

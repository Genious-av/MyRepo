package com.annoproject.scanpackages;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class SwimCoach implements Coach {

	private FortuneService fortuneService;
	
	@Value("${foo.email}")
	private String email;
	
	@Value("${foo.team}")
	private String team;
	
	public SwimCoach(FortuneService thefortuneService) {
		this.fortuneService = thefortuneService;
	}

	@Override
	public String getDailyWorkout() {
		
		return "Swim 1000 meters!";
	}

	@Override
	public String getDailyFortune() {
		
		return fortuneService.getFortune();
	}

	public String getEmail() {
		return email;
	}

	public String getTeam() {
		return team;
	}
	
	
	
	
}

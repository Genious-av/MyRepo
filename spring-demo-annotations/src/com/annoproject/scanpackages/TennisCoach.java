package com.annoproject.scanpackages;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope("singleton")
public class TennisCoach implements Coach {
	@Autowired
	@Qualifier("randomFortuneService")
	private FortuneService fortuneService;
	
	//define a default constructor(not required)
	public TennisCoach() {
		System.out.println("++Inside default constructor");
	}
	
	//define init method
	@PostConstruct
	public void doMyStartUpStaff() {
		System.out.println(">. Inside doMyStartupStaff");
	}
	
	//define destroy method
	@PreDestroy
	public void doMyDestroyStaff() {
		System.out.println(">. Inside doMyDestroyStaff");
	}
	
	//create setter method with any name
		
	/*
	 * @Autowired public void doSomeCrazyStaff(FortuneService theFortuneService) {
	 * System.out.println("Inside setter"); this.fortuneService = theFortuneService;
	 * }
	 */
	
	
	
	/*
	 * @Autowired public TennisCoach(@Qualifier("randomFortuneService") FortuneService theFortuneService) {
	 * this.fortuneService = theFortuneService; }
	 */
	
	

	@Override
	public String getDailyWorkout() {
		return "Practice your backhand volley";
	}

	

	@Override
	public String getDailyFortune() {
		
		return fortuneService.getFortune();
	}

}

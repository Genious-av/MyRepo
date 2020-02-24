package com.fisrtspringproject;

public class LazyCoach implements Coach {
	SuperFortuneService fortuneService;
	
	public LazyCoach(SuperFortuneService fortuneService) {
		super();
		this.fortuneService = fortuneService;
	}


	
	
	@Override
	public String getDailyWorkout() {
		return "Lay two hours on the sofa";
	}

	@Override
	public String getDailyFortune() {
				return fortuneService.getFortune();
	}

}

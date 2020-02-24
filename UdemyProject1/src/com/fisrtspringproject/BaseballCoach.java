package com.fisrtspringproject;


public class BaseballCoach implements Coach {
	private FortuneService fortuneServie;
	
	public BaseballCoach(FortuneService theFortuneServie) {
		fortuneServie=theFortuneServie;
	}
	
	
	
	@Override
	public String getDailyWorkout() {
		return "Spend 30 minutes on batting practice";
	}

	@Override
	public String getDailyFortune() {
	//use myFortuneService
		return fortuneServie.getFortune();
	}

}









package com.fisrtspringproject;


public class TrackCoach implements Coach {
	
	private FortuneService fortuneServie;
	
	
	public TrackCoach() {
		
	}
	
	public TrackCoach(FortuneService theFortuneService) {
		fortuneServie=theFortuneService;
	}
	
	
	
	@Override
	public String getDailyWorkout() {
		return "Run a hard 5k";
	}

	@Override
	public String getDailyFortune() {
		
		return "Just do it"+ fortuneServie.getFortune();
	}
	
	//init method
	public void doMyStartupStaff() {
		System.out.println("TrackCoach: inside startup method");
	}
	
	//destroy method
	public void doMyDestroyStaff() {
		System.out.println("TrackCoach: inside destroy method");
	}
}











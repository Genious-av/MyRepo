package com.fisrtspringproject;

public class CricketCoach implements Coach {
	private FortuneService fortuneService;
	
	private String emailAddress;
	private String team;
	
	
	public CricketCoach() {
		System.out.println("CricketCoach:inside no-args constructor");
	}

	
	
	public String getEmailAddress() {
		return emailAddress;
	}



	public String getTeam() {
		return team;
	}



	public void setFortuneService(FortuneService fortuneService) {
		System.out.println("CricketCoach:inside setter method");
		this.fortuneService = fortuneService;
	}


	public void setEmailAddress(String emailAddress) {
		System.out.println("CricketCoach:inside setEmail method");
		this.emailAddress = emailAddress;
	}

	public void setTeam(String team) {
		System.out.println("CricketCoach:inside setTeam method");
		this.team = team;
	}


	@Override
	public String getDailyWorkout() {
		
		return "Practice fast bowling for 15 minutes";
	}

	@Override
	public String getDailyFortune() {
		
		return fortuneService.getFortune();
	}
	
	

}

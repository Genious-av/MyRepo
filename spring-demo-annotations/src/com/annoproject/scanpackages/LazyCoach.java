package com.annoproject.scanpackages;

import org.springframework.stereotype.Component;

@Component("LazyCoach")
public class LazyCoach implements Coach {

	@Override
	public String getDailyWorkout() {
		return "Have a rest";
	}

	@Override
	public String getDailyFortune() {
		// TODO Auto-generated method stub
		return null;
	}

}

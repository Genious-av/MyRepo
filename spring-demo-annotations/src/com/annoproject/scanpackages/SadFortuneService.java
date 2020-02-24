package com.annoproject.scanpackages;

public class SadFortuneService implements FortuneService {

	@Override
	public String getFortune() {
		
		return "Today is not a sad day!";
	}

}

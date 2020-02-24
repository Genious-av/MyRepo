package com.annoproject.scanpackages;

import java.util.Random;

import org.springframework.stereotype.Component;

@Component
public class RandomFortuneService implements FortuneService {
	private String[] arr= {"Fortune10","Fortune20","Fortune30","Fortune40","Fortune50"};
	
	private Random myRandom=new Random();
	
	@Override
	public String getFortune() {
		int index=myRandom.nextInt(arr.length);
		return arr[index];
	}

}

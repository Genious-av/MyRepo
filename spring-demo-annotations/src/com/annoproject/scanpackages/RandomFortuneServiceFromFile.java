package com.annoproject.scanpackages;

import java.util.Random;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class RandomFortuneServiceFromFile implements FortuneService {
	
	@Value("${foo.fortunes}")
	private String[] arr1;
	
	private String[] arr2;
	
	
	
	
	@PostConstruct
	public void setNewArr() {
		arr2=arr1;
	}

	private Random myRandom=new Random();
	
	@Override
	public String getFortune() {
		int index=myRandom.nextInt(arr2.length);
		return arr2[index];
	}

}

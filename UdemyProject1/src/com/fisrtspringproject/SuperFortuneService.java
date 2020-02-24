package com.fisrtspringproject;

public class SuperFortuneService implements FortuneService {

	@Override
	public String getFortune() {
		String[] fortunes= {"OK","Beseder","SUPER","MEZUYAN"};
		int fort=(0 + (int)(Math.random() * fortunes.length-1));
		System.out.println(fort);
		return fortunes[fort];
	}

}

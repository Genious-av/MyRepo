package com.fisrtspringproject;

import org.springframework.context.support.ClassPathXmlApplicationContext;

public class LifecycleScopeDemo {

	public static void main(String[] args) {
		
		ClassPathXmlApplicationContext context = 
				new ClassPathXmlApplicationContext("bean-lifecycle-applicationContext.xml");
	
		
		Coach theCoach = context.getBean("myCoach", Coach.class);
	
		
		System.out.println(theCoach.getDailyWorkout());
		
		context.close();
	}

}

package com.annoproject.scanpackages;

import org.springframework.context.support.ClassPathXmlApplicationContext;

public class AnnotationDemoApp {

	public static void main(String[] args) {
		//read spring config file
		ClassPathXmlApplicationContext context = 
				new ClassPathXmlApplicationContext("applicationContext.xml");
		//get bean from the spring container\
		SwimCoach theCoach = context.getBean("swimCoach", SwimCoach.class);
		//call method from the bean
		System.out.println(theCoach.getDailyWorkout());
		System.out.println(theCoach.getDailyFortune());
		
		//close the context
		context.close();
	}

}

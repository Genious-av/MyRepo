package com.annoproject.scanpackages;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class JavaConfigDemoApp {

	public static void main(String[] args) {
		//read spring config file
		AnnotationConfigApplicationContext context = 
				new AnnotationConfigApplicationContext(SportConfig.class);
		//get bean from the spring container\
		SwimCoach theCoach = context.getBean("swimCoach", SwimCoach.class);
		//call method from the bean
		System.out.println(theCoach.getDailyWorkout());
		System.out.println(theCoach.getDailyFortune());
		System.out.println(theCoach.getEmail());
		System.out.println(theCoach.getTeam());
		//close the context
		context.close();
	}

}

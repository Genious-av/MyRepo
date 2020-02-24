package com.annoproject.scanpackages;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class FootballJavaConfigDemoApp {

	public static void main(String[] args) {
		//read spring config file
		AnnotationConfigApplicationContext context = 
				new AnnotationConfigApplicationContext(SportConfig.class);
		//get bean from the spring container\
		Coach theCoach = context.getBean("footballCoach", Coach.class);
		//call method from the bean
		System.out.println(theCoach.getDailyWorkout());
		System.out.println(theCoach.getDailyFortune());
		
		//close the context
		context.close();
	}

}

package com.annoproject.scanpackages;

import org.springframework.context.support.ClassPathXmlApplicationContext;

public class AnnotationBeanScopeDemoApp {

	public static void main(String[] args) {
		
		ClassPathXmlApplicationContext context = 
				new ClassPathXmlApplicationContext("applicationContext.xml");
		
		Coach theCoach = context.getBean("swimCoach", Coach.class);
		
		Coach alphaCoach = context.getBean("swimCoach", Coach.class);
		
		boolean result=(theCoach==alphaCoach);
		
		System.out.println("/n pointing to the sameObject "+result);
		System.out.println("/n memory location for Coach "+theCoach);
		System.out.println("/n memory location for Coach "+alphaCoach);
		System.out.println(theCoach.getDailyFortune());
		context.close();
	}

}

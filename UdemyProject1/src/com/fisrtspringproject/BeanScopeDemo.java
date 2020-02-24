package com.fisrtspringproject;

import org.springframework.context.support.ClassPathXmlApplicationContext;

public class BeanScopeDemo {

	public static void main(String[] args) {
		
		ClassPathXmlApplicationContext context = 
				new ClassPathXmlApplicationContext("homeWorkApplicationContext.xml");
	
		
		Coach theCoach = context.getBean("myCoach", Coach.class);
	
		
		Coach alphaCoach = context.getBean("myCoach", Coach.class);
		
		boolean result = (theCoach==alphaCoach);
		System.out.println("\n Pointine at the same object "+result);
		
		System.out.println("\n memory location for coach "+theCoach);
		System.out.println("\n memory location for alhpaCoach "+alphaCoach);
		
		context.close();
	}

}

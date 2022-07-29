package com.luv2code.springdemo;

import org.springframework.context.support.ClassPathXmlApplicationContext;

public class BeanScopeDemoApp {

	public static void main(String[] args) {
		
		//load the spring configuration file
		
		ClassPathXmlApplicationContext context = 
				new ClassPathXmlApplicationContext("beanScope-applicationContext.xml");
		//한번에 많은 config file 로드 할 수도 있다.
		
		// retrieve bean from spring container
		Coach theCoach = context.getBean("myCoach",Coach.class);
		Coach alphaCoach = context.getBean("myCoach",Coach.class);
		
		//check if they are the same
		boolean result = (theCoach == alphaCoach);
		//같은 memory를 가리키고 있는지 확인

		//print out the results
		System.out.println("\nPointing to the same object : " + result);
		
		System.out.println("\nMemory location for theCoach : " + theCoach);
		System.out.println("\nMemory location for alphaCoach : " + alphaCoach + "\n");
		
		//close the context
		context.close();
	}
}

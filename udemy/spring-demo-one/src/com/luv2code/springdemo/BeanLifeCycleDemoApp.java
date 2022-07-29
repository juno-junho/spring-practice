package com.luv2code.springdemo;

import org.springframework.context.support.ClassPathXmlApplicationContext;

public class BeanLifeCycleDemoApp {

	public static void main(String[] args) {
		
		//load the spring configuration file
		
		ClassPathXmlApplicationContext context = 
				new ClassPathXmlApplicationContext("beanLifeCycle-applicationContext.xml");
		//한번에 많은 config file 로드 할 수도 있다.
		
		// retrieve bean from spring container
		Coach theCoach = context.getBean("myCoach",Coach.class);
		Coach bestCoach = context.getBean("myCoach", Coach.class);
		
		System.out.println(theCoach.getDailyWorkout());
		System.out.println(bestCoach.getDailyWorkout());
		System.out.println(theCoach.getDailyFortune());
		System.out.println(bestCoach.getDailyFortune());
		
		boolean result = (theCoach == bestCoach);
		System.out.println(result);
		System.out.println(theCoach);
		System.out.println(bestCoach);
		//close the context
		context.close();
	
	}
}

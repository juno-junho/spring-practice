package com.luv2code.springdemo;

import org.springframework.context.support.ClassPathXmlApplicationContext;

public class HelloSpringApp {

	public static void main(String[] args) {

		// load the spring configuration file
		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
		
		// retrieve bean from spring container
		Coach theCoach = context.getBean("myCoach", Coach.class);
				//					   		beanid  interface.class
		//이 bean이 dependency가 생겼다.(FortuneService)
		//그 FortuneService를 bean에 inject하는 것이다. 
		//그래서 bean을 회수하면 완전히 assembled 조립된 bean을 받는것이다. 
		//spring factory에서 모든 적절한 bean과 dependencies를 만들어 준다. (car factory 처럼) 그래서 그냥 사용만 하면됨.
		
		
		// call methods on the bean
		System.out.println(theCoach.getDailyWorkout());
		
		//let's call our new method for fortunes
		System.out.println(theCoach.getDailyFortune());
		
		
		// close the context
		context.close();

		//1. app should be configurable & simply change config file 2. Easily change the coach for another sport 모두 만족.
	}
}

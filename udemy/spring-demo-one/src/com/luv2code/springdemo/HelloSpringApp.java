package com.luv2code.springdemo;

import org.springframework.context.support.ClassPathXmlApplicationContext;

public class HelloSpringApp {

	public static void main(String[] args) {

		// load the spring configuration file
		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
		
		// retrieve bean from spring container
		Coach theCoach = context.getBean("myCoach", Coach.class);
				//					   		beanid  interface.class
		//�� bean�� dependency�� �����.(FortuneService)
		//�� FortuneService�� bean�� inject�ϴ� ���̴�. 
		//�׷��� bean�� ȸ���ϸ� ������ assembled ������ bean�� �޴°��̴�. 
		//spring factory���� ��� ������ bean�� dependencies�� ����� �ش�. (car factory ó��) �׷��� �׳� ��븸 �ϸ��.
		
		
		// call methods on the bean
		System.out.println(theCoach.getDailyWorkout());
		
		//let's call our new method for fortunes
		System.out.println(theCoach.getDailyFortune());
		
		
		// close the context
		context.close();

		//1. app should be configurable & simply change config file 2. Easily change the coach for another sport ��� ����.
	}
}

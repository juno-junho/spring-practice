package com.luv2code.springdemo;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
//@Scope("singleton")
public class TennisCoach implements Coach {
	// 1. Coach에 새로운 메소드 추가해서 override 밑에 설정.
	// 2. 그리고 FortuneService interface 필드 받아서 파라미터 넘기는 생성자 만든 다음 Autowired 적기.
	private FortuneService fortuneService;
	/*
	@Autowired
	@Qualifier("randomFortuneService")
	private FortuneService fortuneService;
	*/
//	define a default constructor
	public TennisCoach() {
		System.out.println(">> TennisCoach : inside default construtor");
	}
	
	//define my init method
	@PostConstruct
	public void doMyStartupStuff() {
		System.out.println(">> TennisCoach : inside of doMyStartupStuff()");
	}
	//define my destory method
	@PreDestroy
	public void doMyCleanupStuff() {
		System.out.println(">> TennisCoach : inside of doMyCleanupStuff()");
	}
	

	//define a setter method
	//setFortuneService -> doSomeCrazyStuff
	@Autowired
	@Qualifier("happyFortuneService")
	public void doSomeCrazyStuff(FortuneService fortuneService) {
		System.out.println(">> TennisCoach : inside doSomeCrazyStuff() method");
		this.fortuneService = fortuneService;
	}
	

	

 	//constructor injection
	/*@Autowired
	public TennisCoach(@Qualifier("happyFortuneService") FortuneService thefortuneService) {
		fortuneService = thefortuneService;
	}
*/
	
	@Override
	public String getDailyWorkout() {
		return "Practice your backhand volley";
	}

	@Override
	public String getDailyFortune() {
		return fortuneService.getFortune();
	}

}

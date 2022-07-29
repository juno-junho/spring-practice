package com.luv2code.springdemo;

public class BaseballCoach implements Coach{
	
	@Override
	public String getDailyWorkout() {
		return "Spend 30 minutes on batting practice";
	}
	
	//define a private field for the dependency
	private FortuneService fortuneService;
	
	//define a constructor for dependency injection
	public BaseballCoach(FortuneService theFortuneService) {
		fortuneService = theFortuneService;
	}
	
	//spring이 객체를 만들고 dependency를 넘겨줄것이다. 그리고 우리는 accept하고  assign하면 된다.
	//-> dependency injection 하기 준비됨.
	
	
	//위에서 fortuneService 받은것을 사용하는것.
	//helper class를 사용함으로서 Coach를 돕는것. assistatnt 처럼.
	@Override
	public String getDailyFortune() {
		//use my fortuneService to get a fortune
		return fortuneService.getFortune();
	}

}

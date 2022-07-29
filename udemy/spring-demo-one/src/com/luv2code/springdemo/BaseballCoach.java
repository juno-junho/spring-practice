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
	
	//spring�� ��ü�� ����� dependency�� �Ѱ��ٰ��̴�. �׸��� �츮�� accept�ϰ�  assign�ϸ� �ȴ�.
	//-> dependency injection �ϱ� �غ��.
	
	
	//������ fortuneService �������� ����ϴ°�.
	//helper class�� ��������μ� Coach�� ���°�. assistatnt ó��.
	@Override
	public String getDailyFortune() {
		//use my fortuneService to get a fortune
		return fortuneService.getFortune();
	}

}

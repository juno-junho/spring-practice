package com.luv2code.springdemo;

public class TrackCoach implements Coach {
	
	private FortuneService fortuneService;
	
	public TrackCoach() {
		
	}
	
	public TrackCoach(FortuneService fortuneService) {
		this.fortuneService = fortuneService;
	}

	@Override
	public String getDailyWorkout() {
		return "Run a hard 5k";
	}

	@Override
	public String getDailyFortune() {
		return "Just Do It: " + fortuneService.getFortune();
	}
	
	//add an init method (custom hook) 메소드 이름은 뭘해도 상관없다. 그런 관습도 없다. 그냥 하고싶은 이름 붙히면됨
	public void doMyStartupStuff() {
		System.out.println("TrackCoach : inside method doMyStartupStuff");
	}
	//add a destroy method
	public void doMyCleanupStuffYoYo() {
		System.out.println("TrackCoach : inside method doMyCleanupStuffYoYo");
	}
}

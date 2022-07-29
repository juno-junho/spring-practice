package com.luv2code.springdemo;

public class WeightLiftingCoach implements Coach {

	@Override
	public String getDailyWorkout() {
		return "5x5 strengh routine";
	}

	//define a private field for the dependency
		private FortuneService fortuneService;
		
		//define a constructor for dependency injection
		public WeightLiftingCoach(FortuneService thefortuneService) {
			fortuneService = thefortuneService;
		}
		//spring이 객체를 만들고 dependency를 넘겨줄것이다. 그리고 우리는 accept하고  assign하면 된다.
		//-> dependency injection 하기 준비됨.
		@Override
		public String getDailyFortune() {
			
			//use my fortuneService to get a fortune
			
			return fortuneService.getFortune();
		}
}

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
		//spring�� ��ü�� ����� dependency�� �Ѱ��ٰ��̴�. �׸��� �츮�� accept�ϰ�  assign�ϸ� �ȴ�.
		//-> dependency injection �ϱ� �غ��.
		@Override
		public String getDailyFortune() {
			
			//use my fortuneService to get a fortune
			
			return fortuneService.getFortune();
		}
}

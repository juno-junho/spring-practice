package com.luv2code.springdemo;

public interface Coach {
	public String getDailyWorkout();
	
	//Dependency Injection을 위해 추가한 method
	public String getDailyFortune();
	

}

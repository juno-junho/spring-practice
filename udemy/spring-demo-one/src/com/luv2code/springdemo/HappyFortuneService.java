package com.luv2code.springdemo;

import java.util.Random;

public class HappyFortuneService implements FortuneService {
	
	private String[] data = {"Today is your lucky day!",
	                      "There would be a great fortune today!",
	                      "Something Good will happen"};
	
	private Random myRandom = new Random();
	
	//Random을 생성하는 두 가지 방법
	@Override
	public String getFortune() {
		int index = myRandom.nextInt(data.length);
		String theFortune = data[index];
		return theFortune;
	}
	
//	public String getFortune1() {
//		int indexNumber = (int)Math.random()*data.length;
//		return data[indexNumber];
//	}
	

}

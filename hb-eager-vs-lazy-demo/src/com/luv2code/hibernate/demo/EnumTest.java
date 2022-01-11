package com.luv2code.hibernate.demo;

import java.util.EnumSet;
import java.util.HashSet;
import java.util.Set;

enum Framework{
	SPRING, DJANGO, NODEJS, REACT}

enum Season {
	SPRING(0), SUMMER(1), AUTUMN(2), WINTER(3);
	
	private int value;

	private Season(int value) {
		this.value = value;
	}

	public int getValue() {
		return value;
	}

	public void setValue(int value) {
		this.value = value;
	}
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return "overriding toString()" ;
	}
	
}


public class EnumTest {
	private static void printSeason(Season season) {
		switch (season) {
		case SPRING:
			System.out.println("It's spring.");
			break;
		case SUMMER:
			System.out.println("It's summer.");
			break;
		case AUTUMN:
			System.out.println("It's autumn.");
			break;
		case WINTER:
			System.out.println("It's winter");
			break;

		default:
			throw new IllegalArgumentException("You should fill one of the seasons. Try again.");
		}
	}
	public static void main(String[] args) {
		printSeason(Season.WINTER);
		System.out.println(Season.AUTUMN.getValue());
		for (Season e : Season.values()) {
			System.out.println(e.name());	
			System.out.println(e.ordinal());
		}
		System.out.println(Framework.values().length);
		System.out.println("--------------");
		System.out.println(Season.valueOf("SPRING"));
		System.out.println(Enum.valueOf(Season.class, "SPRING"));
		System.out.println("--------------");
		Framework spring = Enum.valueOf(Framework.class, "SPRING");
		
		Framework django = Framework.valueOf("DJANGO");
		System.out.println(spring);
		System.out.println(django);
		System.out.println(Framework.REACT);
		System.out.println("-----------");
	System.out.println(Season.AUTUMN);
	System.out.println(Season.AUTUMN.name());
	System.out.println(Enum.valueOf(Season.class, "AUTUMN"));
	
	System.out.println("-----------");
	System.out.println(Season.AUTUMN.toString());
	System.out.println(Season.SPRING.compareTo(Season.WINTER));
	System.out.println(Season.SPRING == Season.SPRING);
	
	Set<Season> set = new HashSet<> ();
	set.add(Season.SPRING);
	set.add(Season.SUMMER);
	
	EnumSet<Season> enumSet = EnumSet.allOf(Season.class);

	
				
	}
	
	
}

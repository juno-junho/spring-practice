package com.luv2code.hibernate.demo;

public enum RegularPolygon {
	TRIANGLE(new int[] {1,2}) ,SQUARE(new int[]{4,5,6}), PENTAGON(new int[]{7,8,9}), HEXAGON(new int[]{10,11,12});
	
	private int[] arr;
	
	public int[] getArr() {
		return arr;
	}

	public void setArr(int[] arr) {
		this.arr = arr;
	}

	private RegularPolygon(int[] arr) {
		this.arr = arr;
	}

	public static void main(String[] args) {
	
		
	}
	}

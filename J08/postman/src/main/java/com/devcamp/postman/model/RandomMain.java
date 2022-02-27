package com.devcamp.postman.model;

public class RandomMain {
	public static double randomNumber() {
	     double x = Math.random();
	     return x;
	}
	public static double randomNumber(int max, int min) {
		
	     double x = (Math.random()) * ((max - min) + 1) + min;
	     return x;
	}
	public static int randomNumberInt(int max, int min) {
	     int  x = (int)((Math.random()) * ((max - min) + 1) + min);
	     return x;
	}
}

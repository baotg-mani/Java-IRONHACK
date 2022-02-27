package com.devcamp;

public class CCat extends CPet {
	public CCat(String name, int age, int leg, String color) {
		super(name, age, leg, color);
	}
	public CCat() {
		name = "Poly";
	}
	
	public String bark() {
		return "Meo meo";
	} 
	public void print() {
		System.out.println("My name is " + super.name);
	}
	public void play() {
		System.out.println("Play with me");
	}
}

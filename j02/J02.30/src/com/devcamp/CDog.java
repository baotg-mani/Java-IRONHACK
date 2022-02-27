package com.devcamp;

public class CDog extends CPet {
//	constructor
	public CDog(String name, int age, int leg, String color) {
		super(name, age, leg, color);
	}
	
	@Override
	public String bark() {
		return "gau gau";
	}  
	public void print() {
		System.out.println("My name is " + name);
	}
	public void play() {
		System.out.println("choi voi toi");
	}
}

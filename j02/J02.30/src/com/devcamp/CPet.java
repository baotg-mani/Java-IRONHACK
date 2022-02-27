package com.devcamp;

public class CPet {
	protected String name;
	private int age;
	private String color;
	private int leg;
	
//	constructor
	public CPet() {
		
	}
	
//	constructor has params
	public CPet(String paraName, int paramAge, int paramLeg, String paramColor) {
		this.name = paraName;
		this.age = paramAge;
		this.leg = paramLeg;
		this.color = paramColor;
	}
	
//	method set get age
	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}
	
//	method print
	public void print() {
		System.out.println("My pet: " + this.name);
		System.out.println("Corlor: " + this.color);
		System.out.println("Age: " + this.age);
	}
	
	protected String bark() {
		return "bark";
	}
	
	protected void play() {
		System.out.println("Play something");
	}
	


	
}
